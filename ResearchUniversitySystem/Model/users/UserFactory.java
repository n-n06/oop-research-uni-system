package users;

import enums.UserType;
import enums.Degree;
import enums.Gender;
import enums.TeacherType;
import users.students.*;
import users.employees.*;

public class UserFactory {

    public UserFactory() {
    }

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
}