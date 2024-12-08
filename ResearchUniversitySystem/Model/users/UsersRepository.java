package users;

import java.util.TreeSet;
import java.util.HashMap;

/**
 * 
 */
public class UsersRepository {

    /**
     * Default constructor
     */
    public UsersRepository() {
    }

    /**
     * 
     */
    private TreeSet<User> users;

    /**
     * 
     */
    private HashMap<User, Boolean> verified;

    /**
     * @param user 
     * @return
     */
    public void addUser(BaseUser user) {
        // TODO implement here
        return;
    }

    /**
     * @param user 
     * @return
     */
    public boolean removeUser(User user) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void updateUser() {
        // TODO implement here
        return;
    }

    /**
     * @param user 
     * @return
     */
    public User getUser(User user) {
        // TODO implement here
        return null;
    }

    /**
     * @param user 
     * @return
     */
    public boolean isVerified(User user) {
        // TODO implement here
        return false;
    }

    /**
     * @param email 
     * @param password 
     * @return
     */
    public boolean login(String email, String password) {
        // TODO implement here
        return false;
    }

}