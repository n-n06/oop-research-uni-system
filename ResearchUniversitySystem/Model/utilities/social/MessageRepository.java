package utilities.social;

import java.util.HashMap;
import java.util.TreeSet;

import users.User;
import utilities.social.Message;

public class MessageRepository {
    /**
     * Map from a user to a sorted set of unique messages
     * 
     */
    private HashMap<User, TreeSet<Message>> userMessages;
	
	
    /**
     * Default constructor
     */
    public MessageRepository() {
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
        for (Message m : getMessages(user)) {
        	System.out.println(m);
        };
    }


}
