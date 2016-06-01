
package tweeter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;

/**
 * This class gets twits from the database and also gets twits with hashtags and 
 * mentions. 
 * @author Bryan
 */
public class TwitDispatcher {
    
    /**
     * Default constructor.
     */
    public TwitDispatcher() {
        
    }
    
    //Sql statement to add twits into database
    public final String send_twit_sql = "INSERT INTO twits (user_id, message, "
                                      + "timestamp, has_hashtag, is_private) "
                                      + "values (?, ?, ?, ?, ?)";
    
    /**
     * This method adds hashtags and mentions to the database and  
     * @param user the user associated with the twit
     * @param message the message of the twit
     * @param isPrivate true or false if message is private
     */
    public void dispatch(User user, String message, boolean isPrivate)  {
        
        boolean hasHashtag = false;

        //loops through message to find hashtags
        for (int i = 0; i != message.length(); ++i) {
            String currentHash = "";
            //if the character isn't a #, keep looping
            if (message.charAt(i) != '#') 
                continue;
            
            //starts at the character after # to set the hashtag word 
            for (int j = i+1; j < message.length(); ++j) {
                char currentLetter = message.charAt(j);
                //if a space or # is found, breaks out of for loop
                if (currentLetter == ' ' || currentLetter == '#') {
                    break;
                }
                currentHash += currentLetter;
            }//end nested for
            //if there is a hashtag, set the value
            if (currentHash.length() >= 1) {
                hasHashtag = true;
                setHashtag(currentHash);
            }
        }//end for
              
        // compute timestamp
        LocalDateTime timestamp = LocalDateTime.now();
        String timestampString = timestamp.toString().replace('T', ' ');
        
        int userId = Integer.parseInt(user.getUserId());
        //creates an object for sending the sql statement to the database
        PreparedStatement insertTwit = null;
        
        try{
            //insert the twit into the database, returns the twit id 
            insertTwit = getConnection().prepareStatement(send_twit_sql, Statement.RETURN_GENERATED_KEYS);
            insertTwit.setInt(1, userId);
            insertTwit.setString(2, message);
            insertTwit.setString(3, timestampString);
            insertTwit.setBoolean(4, hasHashtag);
            insertTwit.setBoolean(5, isPrivate);
            insertTwit.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        int twitId = -1;
        
        try {
            //gets the twit id when a twit is inserted to link the mentions and hashtags
            ResultSet generatedKeys = insertTwit.getGeneratedKeys();
            if (generatedKeys.next()) {
                twitId = (int) generatedKeys.getLong(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TwitDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        //loops through message looking for @ symbol and adds the characters after to currentMention
        for (int i = 0; i != message.length(); ++i) {
            String currentMention = "";
            //if the character isn't an @, keep looping
            if (message.charAt(i) != '@') 
                continue;
            
            //starts at the character after @ to set the mention variable
            for (int j = i+1; j < message.length(); ++j) {
                char currentLetter = message.charAt(j);
                //if a space or @ is found, breaks out of for loop
                if (currentLetter == ' ' || currentLetter == '@') {
                    break;
                }
                currentMention += currentLetter;
            }//end nested for
            
            //if there is a mention, and the recipient exists, set the mention
            if (currentMention.length() >= 1) {
                UserCore userCore = new UserCore();
                User recipient = userCore.getUser(currentMention);
                //if recipient isn't found, keep looping
                if (recipient == null)
                    continue;
                setMention(user, recipient, twitId);
            }
        }//end for
    }//end dispatch method
    
    
    
    //Sql statement for adding mentions into database
    private final String insertMentionSql = "INSERT INTO mentions (sender_id, "
            + "recipient_id, twit_id) VALUES ( ?, ?, ? )";
    
    /**
     * This method sets the mention in the database. 
     * @param user the user who added the mention
     * @param recipient the user associated with the mention
     * @param twitId  the id of the twit
     */
    public void setMention(User user, User recipient, int twitId) {
        int senderId = Integer.parseInt(user.getUserId());
        int recipientId = Integer.parseInt(recipient.getUserId());
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement insertMention;
            insertMention = getConnection().prepareStatement(insertMentionSql);
            insertMention.setInt(1, senderId);
            insertMention.setInt(2, recipientId);
            insertMention.setInt(3, twitId);
            insertMention.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end setMention method
    
    /**
     * This method checks to see if the hash exists and if so, calls the function
     * to increment the count of the hashtag.
     * @param hashtag the hashtag value to be incremented
     */
    public void setHashtag(String hashtag) {
        boolean hashExists = getHashtag(hashtag);
        if (hashExists) {
            //increments the hashtag 
            updateHashtag(hashtag);
            return;
        }
        insertHashtag(hashtag);
    }//end setHashtag method
    
    //Sql string to insert hashtags into the database
    private final String insertHashtagSql = "INSERT INTO hashtags (phrase) VALUES ( ? )";
    
    /**
     * This method inserts the hashtag into the database if the hashtag doesn't exist.
     * @param hashtag the value to be inserted into database
     */
    public void insertHashtag(String hashtag) {
        
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement insertHashTag;
            insertHashTag = getConnection().prepareStatement(insertHashtagSql);
            insertHashTag.setString(1, hashtag);
            insertHashTag.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end insertHashtag method
    
    //Sql string to update the hashtag in the database
    private final String updateHashtagSql = "UPDATE hashtags SET count = count + 1 WHERE phrase = ?";
    
    /**
     * This method increments the hashtags in the database.
     * @param hashtag the hashtag to be incremented
     */
    private void updateHashtag(String hashtag) {
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement insertHashTag;
            insertHashTag = getConnection().prepareStatement(updateHashtagSql);
            insertHashTag.setString(1, hashtag);
            insertHashTag.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end updateHashtag method
    
    //Sql string for getting the hashtag from database
    private final String selectHashtagSql = "SELECT * FROM hashtags WHERE phrase = ?";
    
    /**
     * This method checks to see if there is a hashtag in the database.
     * @param hashtag the hashtag to be checked in the database
     * @return the boolean value of if the hashtag exists in the database
     */
    private boolean getHashtag(String hashtag) {
        //creates a null object for referencing the database
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement selectHashTag;
            selectHashTag = getConnection().prepareStatement(selectHashtagSql);
            selectHashTag.setString(1, hashtag);
            resultSet = selectHashTag.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        //determines if there are hashtags
        if (resultSet == null) {
            return false;
        }
        
        try {
            //checks the pointer to the current row of data in database
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TwitDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        //if we get here, hashtag was not found
        return false;
        
    }//end getHashtag method 
}//end TwitDispatcher class
