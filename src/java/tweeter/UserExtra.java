
package tweeter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;
/**
 * This class contains methods for entering information for the interests and about me for a user.
 * @author Bryan
 */
public class UserExtra {
    
    /**
     * Default constructor.
     */
    public UserExtra() {
        
    }
    
    //Sql string to get the about me information from the database
    private final String select_about_me_sql = "SELECT about_me FROM about_users WHERE user_id = ?";
    
    /**
     * This method gets the about me information of the user.
     * @param user the user whose information to get
     * @return the about me information
     */
    public String getAboutMe(User user) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement selectAboutMe;
            selectAboutMe = getConnection().prepareStatement(select_about_me_sql);
            selectAboutMe.setInt(1, userId);
            resultSet = selectAboutMe.executeQuery();            
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        String aboutMe = null;
        try {
            //checks the pointer to the current row of data in database
            if (resultSet.next()) {
                aboutMe = resultSet.getString("about_me");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserExtra.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        return aboutMe;
    }//end getAboutMe method
    
    /**
     * This method sets the about me information of the user.
     * @param user the user whose about me information to set
     * @param aboutMe the about me information
     */
    public void setAboutMe(User user, String aboutMe) {
        boolean insert = false;
        String aboutMeValue = getAboutMe(user);
        if (aboutMeValue == null) {
            insert = true;
        }
        //insert about me data, if it exists update the about me
        if (insert) {
            insertAboutMe(user, aboutMe);
        } else {
            updateAboutMe(user, aboutMe);
        }
    }//end setAboutMe method
    
    //Sql string to insert about me into the database
    private final String insert_about_me_sql = "INSERT INTO about_users (user_id, about_me) VALUES (?, ?)";
    
    /**
     * This method inserts the about me information into the database.
     * @param user the user passed in
     * @param aboutMe the about me information to be entered into database
     */
    private void insertAboutMe(User user, String aboutMe) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement insertAboutMe;
            insertAboutMe = getConnection().prepareStatement(insert_about_me_sql);
            insertAboutMe.setInt(1, userId);
            insertAboutMe.setString(2, aboutMe);
            insertAboutMe.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end insertAboutMe method
     
    //Sql string to update the about me of the user 
    private final String update_about_me_sql = "UPDATE about_users SET about_me = ? WHERE user_id = ?";
    
    /**
     * This method updates the about me of the user in the database.
     * @param user the user whose information will be updated
     * @param aboutMe the about me information to be updated
     */
    private void updateAboutMe(User user, String aboutMe) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updateAboutMe;
            updateAboutMe = getConnection().prepareStatement(update_about_me_sql);
            updateAboutMe.setString(1, aboutMe);
            updateAboutMe.setInt(2, userId);
            updateAboutMe.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end updateAboutMe method
    
    //Sql string to get interests from the database for a user
    private final String select_interests_sql = "SELECT interest FROM interests WHERE user_id = ?";
    
    /**
     * This method gets the interests of the user from the database.
     * @param user the user whose information will be queried
     * @return the interests of the user
     */
    public String getInterests(User user) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement selectInterests;
            selectInterests = getConnection().prepareStatement(select_interests_sql);
            selectInterests.setInt(1, userId);
            resultSet = selectInterests.executeQuery();           
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        String interests = null;
        try {
            //checks the pointer to the current row of data in database
            if (resultSet.next()) {
                interests = resultSet.getString("interest");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserExtra.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        return interests;
    }//end getInterests method
    
    //Sql string to insert interests for a user into the database
    private final String insert_interests_sql = "INSERT INTO interests (user_id, interest) VALUES (?, ?)";
    
    /**
     * This method inserts the interests of the user into the database.
     * @param user the user whose interests will be input
     * @param interest the interests information to be added to the database
     */
    private void insertInterest(User user, String interest) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updateInterests;
            updateInterests = getConnection().prepareStatement(insert_interests_sql);
            updateInterests.setInt(1, userId);
            updateInterests.setString(2, interest);
            updateInterests.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end insertInterest method
    
    //Sql string to update interests for a user into the database
    private final String update_interests_sql = "UPDATE interests SET interest = ? WHERE user_id = ?";
    
    /**
     * This method updates the interests of the user in the database.
     * @param user the user whose information will be updated
     * @param interest the interests to be updated 
     */
    private void updateInterest(User user, String interest) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updateInterests;
            updateInterests = getConnection().prepareStatement(update_interests_sql);
            updateInterests.setString(1, interest);
            updateInterests.setInt(2, userId);
            updateInterests.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end updateInterest method
    
    /**
     * This method sets the interests of the user.
     * @param user the user whose information will be set
     * @param interest the interests to be set
     */
    public void setInterests(User user, String interest) {
        boolean insert = false;
        String interests = getInterests(user);
        if (interests == null) {
            insert = true;
        }
        //insert interests data, if it already exists then update the data
        if (insert) {
            insertInterest(user, interest);
        } else {
            updateInterest(user, interest);
        }
    }//end setInterests method
}//end UserExtra class
