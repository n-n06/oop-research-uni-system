package users.students;

import enums.*;

/**
 * @author eva
 */
public class StudentFactory {
    public static Student createStudent(String firstName, String lastName, int age, Gender gender, Degree degree, School school) {
        if (degree == Degree.BACHELOR) {
            return new BachelorStudent(firstName, lastName, age, gender, school);
        } else if (degree == Degree.MASTER || degree == Degree.PHD) {
            return new GraduateStudent(firstName, lastName, age, gender, degree, school);
        } else {
            throw new IllegalArgumentException("No such degree exists.");
        }
    }
}
