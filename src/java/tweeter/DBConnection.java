
package tweeter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates the connection and maintains the connection to the database.
 * @author Bryan
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://50.87.146.66:3306/cmpgamer_tweeter_db";
    private static final String USERNAME = "cmpgamer_tweeter";
    private static final String PASSWORD = "Cmpgamer1";
    
    /**
     * This loads the database connection when the program starts, but only runs 
     * once. Calling it static makes it do this.
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Connection connection;
    
    /**
     * Default constructor.
     */
    private DBConnection() {
        
    }
    
    /**
     * This method gets the connection for the database, and if the connection is 
     * not there, it tries to get it again.
     * @return the connection to the database
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }//end getConnection method 
}//end DBConnection class
