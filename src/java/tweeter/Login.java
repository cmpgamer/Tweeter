
package tweeter;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tweeter.DBConnection.getConnection;

/**
 * This class gets the proper login information for the user.
 * @author Bryan
 */
public class Login  {
    /**
     * Default constructor.
     */
    public Login(){      
    }
    //Sql call for getting the user
    private final String getUserSql = "SELECT * from users WHERE (account_name = ? AND pass = ?)";
    
    /**
     * This method gets the user from the database based off account name and password.
     * @param account this is the account name of the user
     * @param pass this is the password of the user
     * @return the user associated with the account name and password passed in
     */    
    public User getUser(String account, String pass){
        //creates an object that points to a current row of data
        ResultSet resultSet = null;
        try{
            //creates an object for sending the sql statement to the database
            PreparedStatement selectUser;
            //get user info from sql query
            selectUser = getConnection().prepareStatement(getUserSql);
            selectUser.setString(1, account);      //sets account for getting user
            selectUser.setString(2, pass);         //sets password for getting user
            resultSet = selectUser.executeQuery(); //executes query based on account and password
        }catch(SQLException e){
            e.printStackTrace();
        }//end try catch
        
        //if resultSet doesn't have data, returns null
        if (resultSet == null) 
            return null;
        //sets user to null
        User user = null;
        try {
            //checks the pointer to the current row of data in database
            if (resultSet.next()) {
                //creates a new user object and constructs it with data from the database
                user = new User(
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        
        return user;
    }//end getUser method
    
}//end Login class

