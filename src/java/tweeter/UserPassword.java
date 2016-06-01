
package tweeter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;

/**
 * This class contains the methods for updating the password and validating the passwords.
 * @author Kenny
 */
public class UserPassword {
    /**
     * Default constructor.
     */
    public UserPassword() {
        
    }
    
    //Sql string for updating the password of a user
    private final String update_password_sql = "UPDATE users SET pass = ? WHERE user_id = ?";
    
    /**
     * This method updates the password of the user.
     * @param user the user whose password will be updated
     * @param newPassword the password to be updated
     */    
    public void updatePassword(User user, String newPassword) {
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement updatePassword;
            updatePassword = getConnection().prepareStatement(update_password_sql);
            MD5Hash md5Hash = new MD5Hash();
            String hashtext = md5Hash.hash(newPassword);
            updatePassword.setString(1, hashtext);
            updatePassword.setInt(2, userId);
            updatePassword.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
    }//end updatePassword method
    
    //Sql string to get the password of a user for validation
    private final String validPasswordSql = "SELECT * from users WHERE user_id = ? AND pass = ?";
    
    /**
     * This method checks to see if the password matches.
     * @param user the user whose password will be checked
     * @param password the password to be checked
     * @return boolean of the value if the passwords match or not
     */
    public boolean validPassword(User user, String password) {
        
        int userId = Integer.parseInt(user.getUserId());
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement validPassword;
            validPassword = getConnection().prepareStatement(validPasswordSql);
            validPassword.setInt(1, userId);
            
            MD5Hash md5Hash = new MD5Hash();
            String hashtext = md5Hash.hash(password);
            validPassword.setString(2, hashtext);
            
            resultSet = validPassword.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        if (resultSet == null)
            return false;
        
        try {
            //checks the pointer to the current row of data in database
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(UserPassword.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        return false;
    }//end validPassword method
}//end UserPassword class
