package courses;

import java.io.Serializable;

import users.students.Student;

/**
 * 
 */
public class RegistrationRequest implements Serializable {

    /**
     * Default constructor
     */
    public RegistrationRequest() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private Student student;

    /**
     * 
     */
    private Course course;

    /**
     * 
     */
    private boolean approved;

}