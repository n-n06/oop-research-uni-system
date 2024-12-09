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
        switch (type) {
            case STUDENT:
                return new Student();
            case TEACHER:
                return new Teacher();
            default:
                throw new IllegalArgumentException("Unknown user type: " + type);
        }
    }
    
    public Student makeUser(String firstName, String lastName, String email, UserType type, Degree studentDegree) {

        return new Student();
    }

    public Teacher makeUser(String firstName, String lastName, String email, UserType type, TeacherType teacherType) {
        return new Teacher();
    }
}