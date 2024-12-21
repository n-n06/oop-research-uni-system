package users;

import java.security.SecureRandom;

import enums.*;
import users.students.*;
import utilities.logging.LoggerProvider;
import users.employees.*;

public class UserFactory {
	private final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private final SecureRandom RANDOM = new SecureRandom();
	private final int PASS_LEN = 10;
	
    public UserFactory() {
    }
    
    //Overloaded makeUser methods

    public Employee makeUser(String firstName, String lastName, int age, Gender gender, UserType type) {
    	String pass = generatePassword();
    	Employee e;
        switch (type) {
            case ADMIN:
            	e = new Admin(firstName, lastName, age, gender);
            	break;
            case DEAN:
            	e = new Dean(firstName, lastName, age, gender);
            	break;
            default:
                throw new IllegalArgumentException("Could not create user of type: " + type);
        }
        e.changePassword(pass);
        LoggerProvider.getLogger().info("Created new " + type.toString().toLowerCase() +  ": " + e.getEmail() + " with pass: " + pass);
        return e;
    }
    
    public Manager makeUser(String firstName, String lastName, int age, Gender gender, ManagerType managerType) {
    	String pass = generatePassword();
    	Manager m = new Manager(firstName, lastName, age, gender, managerType);
    	m.changePassword(pass);
    	LoggerProvider.getLogger().info("Created new manager: " + m.getEmail() + " with pass: " + pass);
    	return m;
    }
    
    public Student makeUser(String firstName, String lastName, int age, Gender gender, School school, Degree degree) {
    	Student s;
    	String pass = generatePassword();
        if (degree == Degree.BACHELOR) {
            s = new BachelorStudent(firstName, lastName, age, gender, school);
        } else if (degree == Degree.MASTER || degree == Degree.PHD) {
            s = new GraduateStudent(firstName, lastName, age, gender, degree, school);
        } else {
            throw new IllegalArgumentException("No such degree exists.");
        }
        s.changePassword(pass);
        LoggerProvider.getLogger().info("Created new student: " + s.getEmail() + " with pass: " + pass);
        return s;
    }

    public Teacher makeUser(String firstName, String lastName, int age, Gender gender, School school, TeacherType teacherType) {
    	String pass = generatePassword();
    	Teacher t = new Teacher(firstName, lastName, age, gender, teacherType, school);
    	t.changePassword(pass);
    	LoggerProvider.getLogger().info("Created new teacher: " + t.getEmail() + " with pass: " + pass);
        return t;
    }
    
    
    //Generate passwords
    private String generatePassword() {
    	StringBuilder password = new StringBuilder(PASS_LEN);
    	for (int i = 0; i < PASS_LEN; i++) {
    		int charIndex = RANDOM.nextInt(CHARS.length());
    		password.append(CHARS.charAt(charIndex));
    	}
    	return password.toString();
    }
    
    
    
}