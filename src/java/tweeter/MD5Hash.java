
package tweeter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class stores the passwords in the database as a hashed value.
 * @author Kenny
 */
public class MD5Hash {
    
    /**
     * Default constructor.
     */
    public MD5Hash() {
    }
    
    /**
     * This method takes a string and returns a hashed value of the string.
     * @param input the string to be hashed
     * @return the hashed value
     */
    public String hash(String input) {
        try {
            //Object for using the MD5 hash function
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(input.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            //Zero pads the password for hashing 32 characters
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;  
            }//end while
            return hashtext;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5Hash.class.getName()).log(Level.SEVERE, null, ex);
        }//end try catch
        return null;
    }//end hash method
}//end Hash class
