package utilities.social;

/**
 * @author nurs
 * */

import java.util.Date;

import users.User;

/**
 * */
public class WorkMessage extends Message {
	
	/**
	 * The user who sent this message
	 * */
	private User sender;
	
	
	/**
	 * Default constructor
	 * */
    public WorkMessage() {
    }
    

    public WorkMessage(User receiver, String content) {
    	super(receiver, content);
    }
    
    
    /*
     * Main constructorw 
     * */
    public WorkMessage(User sender, User receiver, String content) {
    	this(receiver, content);
    	this.sender = sender;
    }
    
    @Override
    public String toString() {
    	return "Work " + super.toString() + "\nSender: " + sender.getName();
    }
}
