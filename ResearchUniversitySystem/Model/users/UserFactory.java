package users;

import java.security.SecureRandom;

import enums.*;
import users.students.*;
import users.employees.*;

public class UserFactory {
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom RANDOM = new SecureRandom();
	private static final int PASS_LEN = 10;
	
    public UserFactory() {
    }
    
    //Overloaded makeUser methods

    public BaseUser makeUser(String firstName, String lastName, String email, int age, Gender gender, UserType type) {
        switch (type) {
            case ADMIN:
            	return new Admin(firstName, lastName, email, age, gender);
            case DEAN:
            	return new Dean();
            case MANAGER:
            	return new Manager();
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
    
    public Student makeUser(String firstName, String lastName, String email, UserType type, Degree studentDegree) {
    	if (studentDegree == Degree.BACHELOR) {
    		return new BachelorStudent();
    	} 
    	if (studentDegree == Degree.MASTER) {
    		return new MastersStudent();
    	} 
    	return new PhDStudent();
    }

    public Teacher makeUser(String firstName, String lastName, String email, UserType type, TeacherType teacherType) {
        return new Teacher();
    }
    
    
    
    //Generate passwords
    public String generatePassword() {
    	StringBuilder password = new StringBuilder(PASS_LEN);
    	for (int i = 0; i < PASS_LEN; i++) {
    		int charIndex = RANDOM.nextInt(CHARS.length());
    		password.append(CHARS.charAt(charIndex));
    	}
    	return password.toString();
    }
}