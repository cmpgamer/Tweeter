
package tweeter;

import java.util.Objects;

/**
 * This class stores the information that is related to a user in Tweeter.
 * @author Kenny
 */
public class User {
    
    private String userId;
    private String firstName;
    private String lastName;
    private String accountName;
    private String email;
    private String password;
    private String birthdate;
    private String gender;
    
    /**
     * Default user constructor.
     */
    public User(){
        this(" ", " ", " ", " ", " ", " ", " ", " ");
    }
    
    /**
     * Non default user constructor.
     * @param u This user's user id number.
     * @param f This user's first name.
     * @param l This user's last name.
     * @param a This user's account name.
     * @param e This user's email address.
     * @param p This user's password.
     * @param b This user's birth date.
     * @param g This user's gender.
     */
    public User(String u, String f, String l, String a, String e, String p, String b, String g){
        this.userId = u;
        this.firstName =  f;
        this.lastName = l;
        this.accountName = a;
        this.email = e;
        this.password = p;
        this.birthdate = b;
        this.gender = g;
    }

    public String getAboutMe() {
        return null;
    }
    /**
     * This method returns the user id.
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the user id.
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method gets the first name of the user.
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method sets the first name of the user.
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method gets the last name of the user.
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method sets the last name of the user.
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method gets the account name of the user.
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * This method sets the account name of the user.
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * This method gets the email of the user.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method sets the email of the user.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method gets the password of the user.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the password of the user.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method gets the birth date of the user.
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * This method sets the birth date of the user.
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * This method gets the gender of the user.
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method sets the gender of the user.
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    } 

    @Override
    /**
     * This method overrides the hashCode method.
     */
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.userId);
        hash = 41 * hash + Objects.hashCode(this.firstName);
        hash = 41 * hash + Objects.hashCode(this.lastName);
        hash = 41 * hash + Objects.hashCode(this.accountName);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.password);
        hash = 41 * hash + Objects.hashCode(this.birthdate);
        hash = 41 * hash + Objects.hashCode(this.gender);
        return hash;
    }

    @Override
    /**
     * This method overrides the equals method.
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.accountName, other.accountName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        return true;
    }//end equals override method
    
}//end User class
