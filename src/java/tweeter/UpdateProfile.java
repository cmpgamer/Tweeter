
package tweeter;

import java.sql.PreparedStatement;

/**
 * This class updates the user's information or password.
 * @author Bryan
 */
public class UpdateProfile {
    //creates an object for sending the sql statement to the database
    PreparedStatement updateUser;
    
    /**
     * Default constructor.
     */
    public UpdateProfile() {
        
    }
    
    /**
     * This method updates the user and returns a boolean.
     * @param accountName the account name
     * @param email the email 
     * @param aboutMe string with user about me information
     * @param Interests string with user interests information
     * @return boolean value
     */
    public boolean updateUser(String accountName, String email, String aboutMe, String Interests) {
        String error = "";
        
        if (accountName.equals("")) {
            error += "Username required\n";
        }
        
        if (email.equals("")) {
            error += "Email Address Required\n";
        }
        return true;
    }//end updateUser method
    
    /**
     * This method updates the user's password and returns a boolean.
     * @param oldPassword string with old password 
     * @param newPassword1 string with new password
     * @param newPassword2 string with new password 
     * @return boolean value 
     */
    public boolean updateUserPassword(String oldPassword, String newPassword1, String newPassword2) {
        return true;
    }//end updateUserPassword
}//end updateProfile class
