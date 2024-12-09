package utilities.social;

/**
 * @author nurs
 * */

import java.util.Date;

import users.User;

/**
 * Abstract Message / Notification class 📬
 * Extended by work messages and jounral notifications
 */
public abstract class Message implements Comparable<Message> {
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
	

    /**
     * Default constructor
     */
    public Message() {
    }
    
    public Message(User receiver) {
    	this.receiver = receiver;
    	this.date = new Date();
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
    public String toString() {
    	return String.format("Message №%d: \n", messageId) + content + "\nSent: " + date;
    }
    
    public int compareTo(Message m) {
    	/**
    	 * Comparison in reverse order
    	 * */
    	return -1 * this.date.compareTo(m.date);
    }

}