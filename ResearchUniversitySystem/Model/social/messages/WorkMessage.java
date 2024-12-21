package social.messages;

/**
 * @author nurs
 * */

import java.util.Date;
import java.util.Objects;

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
     * Main constructor uwu
     * */
    public WorkMessage(User sender, User receiver, String content) {
    	this(receiver, content);
    	this.sender = sender;
    }
    
    @Override
    public int hashCode() {
    	return super.hashCode() + Objects.hash(sender);
    }
    
    @Override
    public String toString() {
    	return "🈺✉️Work " + super.toString() + "Sender: " + sender.getFullName() + " " + sender.getEmail() + "\n" + getContent() + "\n";
    }
}
