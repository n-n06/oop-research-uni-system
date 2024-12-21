package users;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.io.Serializable;

import users.employees.Admin;

public class UserRepository implements Serializable {
	private int userId;
	
    public UserRepository() {
    	users = new HashMap<>();
    	userId = 0;
    }

    private HashMap<String, User> users;

    public void addUser(User user) {
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
    
    
    /**
     * Produces an id for a new user based on 
     * the current number of users in the system
     * 
     * @return	userId	a unique identifier of a user
     */
    public int generateUserId() {
    	userId++;
    	return userId;
    }
    
    public void addRootAdmin() {
    	addUser(new Admin("admin@admin.com"));
    }
    
    @Override
    public String toString() {
    	return "Users: " + users;
    }

}