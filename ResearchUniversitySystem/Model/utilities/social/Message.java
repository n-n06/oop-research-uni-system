package utilities.social;
/**
 * @author nurs
 * */

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

import database.Database;
import menuInfo.Journal;
import users.User;


/**
 * Abstract Message / Notification class 📬
 * Extended by work messages and jounral notifications
 */
public abstract class Message implements Comparable<Message>, Serializable {
    /**
     * User to whom the message was sent
     */
    private int messageId;
	
    /**
     * User to whom the message was sent
     */
    private User receiver;

    /**
     * Message itself
     */
    private String content;

    /**
     * Date of creation of message
     */
    private Date date;
	
    {
    	date = new Date();
    	messageId = Database.generateMessageId();
    }

    /**
     * Default constructor
     */
    public Message() {
    }
    
    public Message(User receiver) {
    	this.receiver = receiver;
    }
    
    public Message(User receiver, String content) {
    	this(receiver);
    	this.content = content;
    }
    
    /**
     * Getters and setters
     * */
    
    public String getContent() {
		return content;
	}
    
    public Date getDate() {
		return date;
	}
    
    public User getReceiver() {
		return receiver;
	}
    
    public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
    
    public void setContent(String content) {
		this.content = content;
	}
    
    @Override
    public boolean equals(Object obj) {
    	/**
    	 * Check equality based on the unique ID
    	 * 
    	 * @param 	obj		another message to check for equality
    	 * 
    	 * @return 	true	if the same message
    	 * 			false 	if not the same
    	 * */
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	Message j = (Message) obj;
    	return j.messageId == this.messageId; 
    }
    
    
    @Override
    public String toString() {
    	return String.format("Message №%d: \n", messageId) + content + "\nSent: " + date;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(messageId, receiver, content, date);
    }
    
    public int compareTo(Message m) {
    	/**
    	 * Comparison in non-decreasing order of dates
    	 * */
    	return -1 * this.date.compareTo(m.date);
    }

}