package utilities.social;

import java.util.HashMap;
import java.util.TreeSet;

import users.User;
import utilities.social.Message;

public class MessageRepository {
	
	
    /**
     * Default constructor
     */
    public MessageRepository() {
    }

    /**
     * 
     */
    private HashMap<User, TreeSet<Message>> userMessages;

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
    public boolean removeRequest(User user, Message message) {
        return userMessages.get(user).remove(message);
    }

    /**
     * @param request 
     * @return
     */
    public Request getRequest(Request request) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void displayRequests() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void acceptRequest() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void declineRequest() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void signRequest() {
        // TODO implement here
        return ;
    }

}
}
