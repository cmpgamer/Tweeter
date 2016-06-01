
package tweeter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;

/**
 * This class contains methods for getting subscribers and unsubscribing from existing users.
 * @author Kenny
 */
public class UserCore {
    
    /**
     * Default constructor.
     */
    public UserCore() {
    
    }
    
    //Sql string for getting an existing user from the database
    private final String userExistsSql = "SELECT * FROM users WHERE account_name = ?";
    
    /**
     * This method checks to see if the user exists in the database.
     * @param accountName the name to look for in the database
     * @return boolean value depending on if user exists
     */
    public boolean userExists(String accountName) {
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement userExists;
            userExists = getConnection().prepareStatement(userExistsSql);
            userExists.setString(1, accountName);
            resultSet = userExists.executeQuery();
            
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return false;
        
        try {
            //if user exists, returns true
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        return false;
    }//end userExists method
    
    //Sql string to get users with a certain account name from the database
    private final String getUserSql = "SELECT * FROM users WHERE account_name = ?";
    
    /**
     * This method gets the user from the database.
     * @param username the username of the user to find
     * @return a user the matches the username passed in
     */
    public User getUser(String username) {
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement userExists;
            userExists = getConnection().prepareStatement(getUserSql);
            userExists.setString(1, username);
            resultSet = userExists.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return null;
        
        try {
            //if user exists, returns the user's information
            if (resultSet.next()) {
                return new User(
                    Integer.toString(resultSet.getInt("user_id")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("account_name"),
                    resultSet.getString("email"),
                    resultSet.getString("pass"),
                    resultSet.getDate("birthdate").toString(),
                    resultSet.getString("gender")
                );
            }//end if
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        return null;
    }//end getUser method
    
    //Sql string to get subscribers for a user
    private static final String getSubscribeesSql = "SELECT * FROM subscribers JOIN users ON users.user_id = subscribers.user_b_id WHERE user_a_id = ?";
    
    /**
     * This method gets the subscribers to a user.
     * @param user the user to get subscribees from
     * @return an arraylist of the subscribees to the user 
     */
    public static ArrayList<User> getSubscribees(User user) {
        ArrayList<User> subscribees = new ArrayList<User>();
        
        int userId = Integer.parseInt(user.getUserId());
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement userExists;
            userExists = getConnection().prepareStatement(getSubscribeesSql);
            userExists.setInt(1, userId);
            resultSet = userExists.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (resultSet == null)
            return subscribees;
        
        try {
            //cycle through the rows of data in the database
            while (resultSet.next()) {
                subscribees.add(new User(
                    Integer.toString(resultSet.getInt("user_id")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("account_name"),
                    resultSet.getString("email"),
                    resultSet.getString("pass"),
                    resultSet.getDate("birthdate").toString(),
                    resultSet.getString("gender")
                ));
            }//end if
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        return subscribees;
    }//end getSubscribees method
    
    //Sql string for inserting a subscriber to another user into the database
    private final static String subscribeSql = "INSERT INTO subscribers (user_a_id, user_b_id) VALUES (?, ?)";
    
    /**
     * This method allows a user to subscribe to another user.
     * @param user the logged in user
     * @param otherUser the user that is subscribing to the user
     */
    public static void subscribe(User user, User otherUser) {
        int userId = Integer.parseInt(user.getUserId());
        int otherId = Integer.parseInt(otherUser.getUserId());
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updateSubscribers;
            updateSubscribers = getConnection().prepareStatement(subscribeSql);
            updateSubscribers.setInt(1, userId);
            updateSubscribers.setInt(2, otherId);
            updateSubscribers.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end subscribe method
    
    //Sql string to unsubscribe a user from another user    
    private final static String unsubscribeSql = "DELETE FROM subscribers WHERE user_a_id = ? AND user_b_id = ?";
    
    /**
     * This method unsubscribes a user from another user.
     * @param user the user who wants to unsubscribe
     * @param otherUser the user to be unsubscribed from
     */
    public static void unsubscribe(User user, User otherUser) {
        //the id variables of the users
        int userId = Integer.parseInt(user.getUserId());
        int otherId = Integer.parseInt(otherUser.getUserId());
        
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updateSubscribers;
            updateSubscribers = getConnection().prepareStatement(unsubscribeSql);
            updateSubscribers.setInt(1, userId);
            updateSubscribers.setInt(2, otherId);
            updateSubscribers.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end unsubscribe method
    
    //Sql string to see if user a is subscribed to user b
    private final static String isSubscribedSql = "SELECT * FROM subscribers WHERE user_a_id = ? AND user_b_id = ?";
    
    /**
     * This method checks to see if user is subscribed to otherUser.
     * @param user the user to check 
     * @param otherUser the otherUser to check   
     * @return boolean value if user is subscribed to otherUser
     */
    public static boolean isSubscribed(User user, User otherUser) {
        if (otherUser == null || user == null) 
            return false;
        //the id variables of the users
        int userId = Integer.parseInt(user.getUserId());
        int otherId = Integer.parseInt(otherUser.getUserId());
        
        //creates an object that points to a current row of data
        ResultSet results = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement isSubscribedQuery;
            isSubscribedQuery = getConnection().prepareStatement(isSubscribedSql);
            isSubscribedQuery.setInt(1, userId);
            isSubscribedQuery.setInt(2, otherId);
            results = isSubscribedQuery.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        if (results == null) {
            return false;
        }
        try {
            //checks the pointer to the current row of data in database
            if (results.next()) {
                return true;
            }   
        } catch (SQLException ex) {
            Logger.getLogger(UserCore.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        return false;
    }//end isSubscribed method
    
}//end UserCore class
