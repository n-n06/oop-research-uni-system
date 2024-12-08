package users;

import enums.UserType;
import enums.Degree;
import enums.TeacherType;
import users.students.Student;
import users.employees.Teacher;

/**
 * 
 */
public class UserFactory {

    /**
     * Default constructor
     */
    public UserFactory() {
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param email 
     * @param type 
     * @return
     */
    public BaseUser makeUser(String firstName, String lastName, String email, UserType type) {
        // TODO implement here
        return null;
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param email 
     * @param type 
     * @param studentType 
     * @return
     */
    public Student makeUser(String firstName, String lastName, String email, UserType type, Degree studentType) {
        // TODO implement here
        return null;
    }

    /**
     * @param firstName 
     * @param lastName 
     * @param email 
     * @param type 
     * @param teacherType 
     * @return
     */
    public Teacher makeUser(String firstName, String lastName, String email, UserType type, TeacherType teacherType) {
        // TODO implement here
        return null;
    }

}