package users;

import java.util.TreeSet;
import java.util.HashMap;

public class UsersRepository {
	
    public UsersRepository() {
    }

    private TreeSet<User> users;

    private HashMap<User, Boolean> verified;

    public void addUser(BaseUser user) {
        return;
    }

    public boolean removeUser(User user) {
        return false;
    }

    public void updateUser() {
        return;
    }


    public User getUser(User user) {
        return null;
    }

    public boolean isVerified(User user) {
        return false;
    }

    public boolean login(String email, String password) {
        return false;
    }

}