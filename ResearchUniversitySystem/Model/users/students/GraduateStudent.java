package users.students;

import enums.*;
import users.employees.Teacher;


/**
 * 
 */
public class GraduateStudent extends Student {

    /**
     * Default constructor
     */
    public GraduateStudent() {
    }
    
    public GraduateStudent(String firstName, String lastName, int age, Gender gender, Degree degree, School school) {
    	super(firstName, lastName, age, gender, degree, school);
    }

    /**
     * 
     */
    private int year;

    /**
     * @param supervisor 
     * @return
     */
    public void assignSupervisor(Teacher supervisor) {
        // TODO implement here
        return;
    }



}