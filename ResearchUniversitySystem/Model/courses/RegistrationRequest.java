package courses;

import java.io.Serializable;

import users.students.Student;

/**
 * @author Muslik
 */
public class RegistrationRequest implements Serializable {
 
    private int regRequestid;
    private Student student;
    private Course course;
    private boolean approved = false;
	
	
    public RegistrationRequest(int id, Course course, Student student) {
    	this.regRequestid = id;
    	this.course = course;
    	this.student = student;
    }

    public int getRegRequestid() {
		return regRequestid;
	}
    
    public void setApproved() {
    	this.approved = true;
    }
    
    public Student getStudent() {
    	return student;
    }
    
    public Course getCourse() {
    	return course;
    }
    
    public String toString() {
    	return "requestID: " + regRequestid + " || " + course.toString() + " || Student: " + student.getEmail() + " || approved: " + approved;
    }

}