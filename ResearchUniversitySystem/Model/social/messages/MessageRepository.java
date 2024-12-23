package social.messages;

import java.util.HashMap;
import java.io.Serializable;
import java.util.TreeSet;

import users.User;

public class MessageRepository implements Serializable{
    private int messageId;
	/**
     * Map from a user to a sorted set of unique messages
     * 
     */
    private HashMap<User, TreeSet<Message>> userMessages;
	
	
    /**
     * Default constructor
     */
    public MessageRepository() {
    	userMessages = new HashMap<User, TreeSet<Message>>();
    	messageId = 0;
    }

    /**
     * @param	user	receiver of the message	 
     * @param	message		message to be sent
     *
     * @return null
     */
    public void addMessage(User user, Message message) {
        userMessages.get(user).add(message);
    }

    /**
     * @param	user	receiver of the message	 
     * @param	message		message to be removed
     *
     * @return	true if message was removed successfully,
     * 			false otherwise
     */
    public boolean removeMessage(User user, Message message) {
        return userMessages.get(user).remove(message);
    }

    /**
     * @param request 
     * @return
     */
    public TreeSet<Message> getMessages(User user) {
        return userMessages.get(user);
    }

    /**
     * @return
     */
    public void viewMessages(User user) {
    	System.out.println("📬Inbox: ");
    	if (getMessages(user) == null) {
    		System.out.println("Inbox empty");
    		return;
    	}
        for (Message m : getMessages(user)) {
        	System.out.println(m);
        };
    }
    
    /**
     * Produces an id for a new message instance based on 
     * the current number of mesage in the system
     * 
     * @return	messageId	a unique identifier of a message
     */
    public int generateMessageId() {
    	messageId++;
    	return messageId;
    }


}
