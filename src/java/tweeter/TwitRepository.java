
package tweeter;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;
/**
 * This class gets twits from the database based on different parameters.
 * @author Bryan
 */
public class TwitRepository  {
    
    /**
     * Default constructor.
     */
    public TwitRepository() {
        
    }
    
    //Sql string for getting public twits from the database queries 
    private static final String publicTwitsSql = "SELECT * FROM twits JOIN  users"
            + " ON users.user_id = twits.user_id WHERE users.user_id = ? AND is_private = 0";
    //Sql string for getting private twits from the database
    private static final String privateTwitsSql = "SELECT * FROM twits JOIN  users"
            + " ON users.user_id = twits.user_id WHERE users.user_id = ? AND is_private = 1";
    
    /**
     * This method gets the list of twits from the database.
     * @param user the user who is requesting the list of twits
     * @param isPrivate the boolean value determining if the twits are private
     * @return the list of twits
     */
    private static ArrayList<Twit> getTwits(User user, boolean isPrivate) {
        //make an array list of twits
        ArrayList<Twit> publicTwits = new ArrayList<Twit>(); 
        int userId = Integer.parseInt(user.getUserId());
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement userExists;
            userExists = getConnection().prepareStatement(
                    isPrivate ? privateTwitsSql : publicTwitsSql
            );
            userExists.setInt(1, userId);
            resultSet = userExists.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return publicTwits;
        
        try {
            //while there is another line in the database, get the list of twits to be
            //added to the public twits array list
            while (resultSet.next()) {
                
                Date date = resultSet.getDate("timestamp");
                LocalDate localdate = date.toLocalDate();
                Time time = resultSet.getTime("timestamp");
                LocalTime localtime = time.toLocalTime();
                
                LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                publicTwits.add(new Twit(
                    resultSet.getInt("twits_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("account_name"),    
                    resultSet.getString("message"),
                    localDateTime,
                    resultSet.getBoolean("has_hashtag"),
                    resultSet.getBoolean("is_private")
                ));
            }//end while
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        //returns the arraylist of public twits added
        return publicTwits;
    }//end getTwits method
    
    //Sql string for searching private twits for a hashtag
    private static final String searchPrivateHashTwitsSql = "SELECT * FROM twits JOIN"
            + "  users ON users.user_id = twits.user_id WHERE has_hashtag = 1 AND is_private = 1 AND users.user_id = ?";
    
    /**
     * This method gets all the private twits that contain the hash from the database.
     * @param hash the hash value to search for
     * @param user the 
     * @return 
     */
    public static ArrayList<Twit> getPrivateHashTwits(String hash, User user){
        ArrayList<Twit> hashTwits = new ArrayList<>();
        if (user == null)
            return hashTwits;
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement hashTwitList = getConnection().prepareStatement(searchPrivateHashTwitsSql);
            hashTwitList.setString(1, user.getUserId());
            resultSet = hashTwitList.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null){
            return hashTwits;
        }
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                String message = resultSet.getString("message");
                if (message.contains(hash)){
                
                    Date date = resultSet.getDate("timestamp");
                    LocalDate localdate = date.toLocalDate();
                    Time time = resultSet.getTime("timestamp");
                    LocalTime localtime = time.toLocalTime();

                    LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                    hashTwits.add(new Twit(
                        resultSet.getInt("twits_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("account_name"),    
                        message,
                        localDateTime,
                        resultSet.getBoolean("has_hashtag"),
                        resultSet.getBoolean("is_private")
                    ));
                }//end if
            }//end while
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
               
        return hashTwits;
    }//end getPrivateHashTwits method
    
    //Sql string for searching public twits for a hashtag in the database
    private static final String searchPublicHashTwitsSql = "SELECT * FROM twits JOIN"
            + "  users ON users.user_id = twits.user_id WHERE has_hashtag = 1 AND is_private = 0";
    
    /**
     * This method gets public twits that contain the hashtag in the array list.
     * @param hash the hashtag to be searched for
     * @return an arraylist of the twits that contain the hashtag
     */
    public static ArrayList<Twit> getPublicHashTwits(String hash){
        ArrayList<Twit> hashTwits = new ArrayList<>();
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement hashTwitList = getConnection().prepareStatement(searchPublicHashTwitsSql);
            resultSet = hashTwitList.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null){
            return hashTwits;
        }
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                String message = resultSet.getString("message");
                if (message.contains(hash)){
                
                    Date date = resultSet.getDate("timestamp");
                    LocalDate localdate = date.toLocalDate();
                    Time time = resultSet.getTime("timestamp");
                    LocalTime localtime = time.toLocalTime();

                    LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                    hashTwits.add(new Twit(
                        resultSet.getInt("twits_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("account_name"),    
                        message,
                        localDateTime,
                        resultSet.getBoolean("has_hashtag"),
                        resultSet.getBoolean("is_private")
                    ));
                }//end if
            }//end while
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
            
        return hashTwits;
    }//end getPublicHashTwits method
    
    /**
     * This method gets all twits. 
     * @param user the logged in or anonymous user
     * @param anonymous boolean value if a user is logged in or if a user is not logged in
     * @return an arraylist of twits 
     */
    private static ArrayList<Twit> getAllTwits(User user, boolean anonymous) {
        //gets private twits
        ArrayList<Twit> privateTwits = new ArrayList<Twit>();
        if (!anonymous) {
            privateTwits = getTwits(user, true);
        }
        //gets public twits
        ArrayList<Twit> publicTwits = getTwits(user, false);
        
        ArrayList<Twit> allTwits = new ArrayList<Twit>(privateTwits);
        allTwits.addAll(publicTwits);
        
        return allTwits;
    }//end getAllTwits method
    
    //Sql string to get mentioned twits from the database
    private static final String mentionedTwitsSql = "SELECT * FROM users u JOIN "
        + "mentions ON u.user_id = mentions.recipient_id JOIN twits ON mentions.twit_id= twits.twits_id"
            + " JOIN users us ON us.user_id = mentions.sender_id WHERE u.user_id = ?";
    
    /**
     * This method gets twits with mentions in them.
     * @param user the mentioned user
     * @return mentioned twits
     */
    public static ArrayList<Twit> getMentionedTwits(User user) {
        ArrayList<Twit> mentionedTwits = new ArrayList<Twit> ();
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement mentionedTwitStatement;
            mentionedTwitStatement = getConnection().prepareStatement(mentionedTwitsSql);
            mentionedTwitStatement.setInt(1, Integer.parseInt(user.getUserId()));
            resultSet = mentionedTwitStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        //if no mentions are found, return arraylist
        if (resultSet == null)
            return mentionedTwits;
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                
                Date date = resultSet.getDate("timestamp");
                LocalDate localdate = date.toLocalDate();
                Time time = resultSet.getTime("timestamp");
                LocalTime localtime = time.toLocalTime();
                
                LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                mentionedTwits.add(new Twit(
                    resultSet.getInt("twits_id"),
                    resultSet.getInt("us.user_id"),
                    resultSet.getString("us.account_name"),    
                    resultSet.getString("message"),
                    localDateTime,
                    resultSet.getBoolean("has_hashtag"),
                    resultSet.getBoolean("is_private")
                ));
            }//end while
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch 
        
        return mentionedTwits;
    }//end getMentionedTwits method
    
    //Sql string to get public mentioned twits
    private static final String publicMentionedTwitsSql = "SELECT * FROM users u JOIN "
            + "mentions ON u.user_id = mentions.recipient_id JOIN twits ON mentions.twit_id= twits.twits_id "
            + "JOIN users us ON us.user_id = mentions.sender_id WHERE( u.user_id = ? AND is_private = 0)";
    
    /**
     * This method gets all public mentioned twits.
     * @param user the user
     * @return an arraylist twits
     */
    private static ArrayList<Twit> getPublicMentionedTwits(User user){
        ArrayList<Twit> mentionedTwits = new ArrayList<Twit> ();
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement mentionedTwitStatement;
            mentionedTwitStatement = getConnection().prepareStatement(publicMentionedTwitsSql);
            mentionedTwitStatement.setInt(1, Integer.parseInt(user.getUserId()));
            resultSet = mentionedTwitStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return mentionedTwits;
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                
                Date date = resultSet.getDate("timestamp");
                LocalDate localdate = date.toLocalDate();
                Time time = resultSet.getTime("timestamp");
                LocalTime localtime = time.toLocalTime();
                
                LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                mentionedTwits.add(new Twit(
                    resultSet.getInt("twits_id"),
                    resultSet.getInt("us.user_id"),
                    resultSet.getString("us.account_name"),    
                    resultSet.getString("message"),
                    localDateTime,
                    resultSet.getBoolean("has_hashtag"),
                    resultSet.getBoolean("is_private")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch 
        
        return mentionedTwits;
    }//end getPublicMentionedTwits method
    
    //Sql string to get all public twits from the database
    private static final String allPublicTwitsSql = "SELECT * FROM twits JOIN  users"
            + " ON users.user_id = twits.user_id WHERE is_private = 0";
    
    /**
     * This method gets all public twits.
     * @return an arraylist of twits
     */
    private static ArrayList<Twit> getPublicTwits() {
        ArrayList<Twit> publicTwits = new ArrayList<Twit> ();
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement userExists;
            userExists = getConnection().prepareStatement(allPublicTwitsSql);
            resultSet = userExists.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return publicTwits;
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                
                Date date = resultSet.getDate("timestamp");
                LocalDate localdate = date.toLocalDate();
                Time time = resultSet.getTime("timestamp");
                LocalTime localtime = time.toLocalTime();
                
                LocalDateTime localDateTime = LocalDateTime.of(localdate, localtime);
                publicTwits.add(new Twit(
                    resultSet.getInt("twits_id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("account_name"),    
                    resultSet.getString("message"),
                    localDateTime,
                    resultSet.getBoolean("has_hashtag"),
                    resultSet.getBoolean("is_private")
                ));
            }//end while
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch  
        return publicTwits;
    }//end getPublicTwits method
    
    /**
     * This method gets the twits to be displayed on the logged in.
     * @param currentUser the current user
     * @param anonymous boolean value if anonymous
     * @param user the user
     * @param filter personal or public string
     * @return an arraylist of twits 
     */
    public static ArrayList<Twit> getProfileTwits(User currentUser, boolean anonymous, User user, String filter) {
        ArrayList<Twit> allTwits = new ArrayList<Twit>();
        
        ArrayList<Twit> mentionedTwits = new ArrayList<Twit>();
        
        //if statement for getting personal twits
        if (!anonymous && (!filter.equals("Public") || !currentUser.equals(user))) {
            ArrayList<Twit> publicTwits;
            publicTwits = getTwits(currentUser, false);
            
            allTwits.addAll(publicTwits);            
            allTwits.addAll(mentionedTwits);
            
            if (currentUser.equals(user)) {
                                
                ArrayList<Twit> privateTwits;
                privateTwits = getTwits(currentUser, true);
                allTwits.addAll(privateTwits);
                
                ArrayList<User> subscribees = new ArrayList<User>();
                if (currentUser.equals(user)) {
                    subscribees = UserCore.getSubscribees(user);

                    for (User subscribee : subscribees) {
                        ArrayList<Twit> subscribeeTwits = getAllTwits(subscribee, anonymous);
                        allTwits.addAll(subscribeeTwits);
                    }//end nested for           
                }//end 2nd nested if
            }//end nested if
            
            if (UserCore.isSubscribed(user, currentUser)) {
                  allTwits.addAll(getTwits(currentUser, true));
                  mentionedTwits = getMentionedTwits(currentUser);
            } else {
                mentionedTwits = getPublicMentionedTwits(currentUser);
            }//end nested if else
            allTwits.addAll(mentionedTwits);
        }//end if 
        
        //if statement for getting public twits
        if (anonymous || filter.equals("Public") || filter.equals("Both")){
            ArrayList<Twit> publicTwits = getPublicTwits();
            allTwits.addAll(publicTwits);
        }      
               
        removeDuplicates(allTwits);
        sortTwitsByDate(allTwits);
        return allTwits;
    }//end getProfileTwits method
    
    /**
     * The method that removes duplicate twits from the array list.
     * @param twits the array list of twits
     */
    private static void removeDuplicates(ArrayList<Twit> twits) {
        Set<Twit> hs = new HashSet<>();
        hs.addAll(twits);
        twits.clear();
        twits.addAll(hs);
    }//end removeDuplicates method
    
    /**
     * The method that sorts the twits by the date.
     * @param twits the array list of twits
     */
    private static void sortTwitsByDate(ArrayList<Twit> twits) {
        Collections.sort(twits, (Twit t1, Twit t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()));
    }//end sortTwitsByDate method
    
}// end TwitRepository class
