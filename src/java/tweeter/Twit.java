
package tweeter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * This class stores the information about a Twit.
 * @author Kenny
 */
public class Twit {
    private int twitId;              //twit id number
    private int userId;              //user id number 
    private String message;          //twit message
    private LocalDateTime timestamp; //time of twit
    private boolean hasHashtag;      //if the twit has a hashtag
    private boolean isPrivate;       //if the twit is private
    private String accountName;      //account name of user

    /**
     * Default constructor.
     */
    public Twit() {
        
    }
    
    /**
     * The non-default constructor that sets the information associated with a Twit.
     * @param twitId the id of the twit in the database
     * @param userId the id of the user in the database
     * @param accountName the account name of the user
     * @param message the twit message 
     * @param timestamp the time the twit was sent
     * @param hasHashtag if the twit contains hash tags
     * @param isPrivate if the twit is marked private 
     */
    public Twit(int twitId, int userId, String accountName, String message, LocalDateTime timestamp, boolean hasHashtag, boolean isPrivate) {
        this.twitId = twitId;
        this.userId = userId;
        this.accountName = accountName;
        this.message = message;
        this.timestamp = timestamp;
        this.hasHashtag = hasHashtag;
        this.isPrivate = isPrivate;
    }

    /**
     * Gets twit id number.
     * @return the twit id
     */
    public int getTwitId() {
        return twitId;
    }
    
    /**
     * Sets the twit id.
     * @param twitId the id of the twit
     */
    public void setTwitId(int twitId) {
        this.twitId = twitId;
    }
    
    /**
     * Gets user id.
     * @return the id of the user
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * Sets the user id.
     * @param userId the id of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the message string.
     * @return the message string
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the message of the twit.
     * @param message the message of the twit
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Gets timestamp of the twit.
     * @return the time the twit was sent
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Sets the timestamp of the twit.
     * @param timestamp the time the twit was sent
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Gets true or false if the message has a hash tag.
     * @return boolean value if there is a hash tag or not
     */
    public boolean isHasHashtag() {
        return hasHashtag;
    }

    /**
     * Sets the boolean value if the message has a hash tag.
     * @param hasHashtag boolean if message has hash tag
     */
    public void setHasHashtag(boolean hasHashtag) {
        this.hasHashtag = hasHashtag;
    }

    /**
     * Gets value of message being marked private.
     * @return the boolean value of if the message is marked private
     */
    public boolean isIsPrivate() {
        return isPrivate;
    }

    /**
     * Sets boolean value of message being marked private.
     * @param isPrivate if message is marked private
     */
    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Get method for account name.
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Set method for account name.
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    
    /**
     * This method overrides the hashCode method.
     * @return the hashed value
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.twitId;
        hash = 29 * hash + this.userId;
        hash = 29 * hash + Objects.hashCode(this.message);
        hash = 29 * hash + Objects.hashCode(this.timestamp);
        hash = 29 * hash + (this.hasHashtag ? 1 : 0);
        hash = 29 * hash + (this.isPrivate ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.accountName);
        return hash;
    }

    
    /**
     * This method overrides the equals method.
     * @return boolean value of comparison of twit objects
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Twit other = (Twit) obj;
        if (this.twitId != other.twitId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        if (this.hasHashtag != other.hasHashtag) {
            return false;
        }
        if (this.isPrivate != other.isPrivate) {
            return false;
        }
        if (!Objects.equals(this.accountName, other.accountName)) {
            return false;
        }
        return true;
    }//end equals override method
   
}//end Twit class
