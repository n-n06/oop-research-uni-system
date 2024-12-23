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
    public Admin makeUser(String firstName, String lastName, int age, Gender gender) {
    	String pass = generatePassword();
    	Admin e = new Admin(firstName, lastName, age, gender);
    	e.changePassword(pass);
        LoggerProvider.getLogger().info("Created new Admin: " + e.getEmail() + " with pass: " + pass);
        return e;
    }

    public ResearcherEmployee makeUser(String firstName, String lastName, int age, Gender gender, School school) {
    	String pass = generatePassword();
    	ResearcherEmployee e = new ResearcherEmployee(firstName, lastName, age, gender, school);

        e.changePassword(pass);
        LoggerProvider.getLogger().info("Created new Researcher Employee: " + e.getEmail() + " with pass: " + pass);
        return e;
    }
    public Dean makeUser(String firstName, String lastName, int age, Gender gender, UserType userType, School school) {
    	String pass = generatePassword();
    	Dean e = new Dean(firstName, lastName, age, gender, userType, school);

        e.changePassword(pass);
        LoggerProvider.getLogger().info("Created new Dean: " + e.getEmail() + " with pass: " + pass);
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