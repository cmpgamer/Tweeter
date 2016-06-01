
package tweeter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;


/**
 * This class contains the methods for adding a user to the database.
 * @author Bryan
 */
public class Register {
   
    //Sql statement for inserting a new user into database
    private final String sql = "INSERT INTO users (first_name, last_name, account_name, email, pass, birthdate, gender) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    /**
     * Default constructor.
     */
    public Register(){     
    }

    /**
     * This method makes a user to add to the database.
     * @param first the first name of the user
     * @param last the last name of the user
     * @param account the account name of the user
     * @param email the email of the user
     * @param hash the hashed password of the user
     * @param birthdate the birth date of the user 
     * @param gender the gender of the user
     */
    public void makeUser(String first, String last, String account, String email, String hash, String birthdate, String gender){
        int result = 0;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement insertUsers;
            insertUsers = getConnection().prepareStatement(sql);
            insertUsers.setString(1, first);
            insertUsers.setString(2, last);
            insertUsers.setString(3, account);
            insertUsers.setString(4, email);
            insertUsers.setString(5, hash);
            insertUsers.setString(6, birthdate);
            insertUsers.setString(7, gender);
            result = insertUsers.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }//end try catch 
        
    }//end makeUser method
}//end Register class
