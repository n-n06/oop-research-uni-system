package users;

import enums.UserType;
import enums.Degree;
import enums.TeacherType;
import users.students.Student;
import users.employees.Teacher;

public class UserFactory {

    public UserFactory() {
    }
    
    public BaseUser makeUser(String firstName, String lastName, String email, UserType type) {
        return null;
    }

    public Student makeUser(String firstName, String lastName, String email, UserType type, Degree studentType) {
        return null;
    }
    
    public Teacher makeUser(String firstName, String lastName, String email, UserType type, TeacherType teacherType) {
        return null;
    }

}