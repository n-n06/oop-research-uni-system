package users;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.io.Serializable;

import users.employees.Admin;

public class UserRepository implements Serializable {
	
    public UserRepository() {
    	users = new HashMap<>();
    }

    private HashMap<String, User> users;

    public void addUser(BaseUser user) {
    	if (user == null) throw new IllegalArgumentException("User cannot be null");
        users.put(user.getEmail(), user);
    }

    public boolean removeUser(User user) {
    	if (user == null) return false;
    	
    	String userEmail = user.getEmail();
    	if (users.remove(userEmail) != null) {
            return true;
        }
        return false;
    }

    public void updateUser(User updatedUser) {
        if (updatedUser == null) throw new IllegalArgumentException("User cannot be null");
        
        String userEmail = updatedUser.getEmail();
        if (users.containsKey(userEmail)) {
            users.put(userEmail, updatedUser); 
        } else {
            throw new IllegalArgumentException("User with Email " + userEmail + " does not exist");
        }
    }
    
    public void viewAllUsers(Admin a) {
    	for (User u : users.values()) {
    		System.out.println(u);
    	};
    }

    public User getUser(String userEmail) {
        return users.get(userEmail);
    }

    public boolean checkIsActiveUser(String userEmail) {
        return getUser(userEmail).getIsActive();
    }

    public boolean login(String email, String password) {
        User user = getUser(email);  
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false; 
    }
    
    public void addRootAdmin() {
    	addUser(new Admin("admin@admin.com"));
    }

}