package users;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.io.Serializable;
import java.security.SecureRandom;

import users.employees.*;
import users.students.*;
import utilities.exceptions.NoDataException;
import utilities.logging.LoggerProvider;

public class UserRepository implements Serializable {
	private HashMap<String, User> users;
	private int userId;
	private byte[] passwordHashingSalt;
	
    public UserRepository() {
    	users = new HashMap<>();
    	userId = 0;
    	passwordHashingSalt = generateSalt();
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    
    String hashPassword(String password) {
    	try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), passwordHashingSalt, 1024, 256);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hashedPassword = keyFactory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashedPassword);
    	} catch (Exception e) {
    		System.err.println(e.getMessage());
    		return "";
    	}

    }

    public void addUser(User user) {
    	if (user == null) throw new IllegalArgumentException("User cannot be null");
        users.put(user.getEmail(), user);
        LoggerProvider.getLogger().info(user.getEmail() + " was added to the system!");
    }

    public boolean removeUser(User user) {
    	if (user == null) return false;
    	
    	String userEmail = user.getEmail();
    	if (users.remove(userEmail) != null) {
    		LoggerProvider.getLogger().warning("User " + userEmail + " was deleted!");
            return true;
        }
        return false;
    }

    public void updateUser(User updatedUser) {
        if (updatedUser == null) throw new IllegalArgumentException("User cannot be null");
        
        String userEmail = updatedUser.getEmail();
        if (users.containsKey(userEmail)) {
        	LoggerProvider.getLogger().info(userEmail + " was updated!");
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

    public Collection<User> getAllUsers() {
    	return users.values();
    }
    
    public List<Student> getAllStudents() {
    	Predicate<User> isStudent = user -> user instanceof Student;
    	return users.values().stream()
    			.filter(isStudent)
    			.map(user -> (Student) user)
    			.collect(Collectors.toList());
    }
    
    public List<Teacher> getAllTeachers() {
    	Predicate<User> isTeacher = user -> user instanceof Teacher;
    	return users.values().stream().filter(isTeacher)
    			.map(user -> (Teacher) user)
    			.collect(Collectors.toList());
    }
    
    public User getUser(String userEmail) throws NoDataException{
        if (users.get(userEmail) == null) {
        	throw new NoDataException("No such user found");
        }
        return users.get(userEmail);
    }

    
    //Login
    public boolean checkIsActiveUser(String userEmail) throws NoDataException {
        return getUser(userEmail).getIsActive();
    }

    public boolean login(String email, String password) {
    	LoggerProvider.getLogger().info(email + " logged in");
        User user;
		try {
			user = getUser(email);
			user.getPassword().equals(hashPassword(password));
		} catch (NoDataException e) {
			e.printStackTrace();
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
    	Admin root = new Admin("admin@admin.com");
    	root.changePassword("rootPass1234");
    	addUser(root);
    }
    
    @Override
    public String toString() {
    	return "Users: " + users;
    }

}