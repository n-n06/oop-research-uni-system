package courses;

import java.io.Serializable;

import database.Database;
import users.students.Student;

/**
 * @author Muslik
 */
public class RegistrationRequest implements Serializable {
 
    private int regRequestid;
    private Student student;
    private Course course;
    private boolean approved = false;
	
    {
    	this.regRequestid = Database.instance.getRegistration().generateRegRequestId();
    }
	
    public RegistrationRequest(Course course, Student student) {
    	this.course = course;
    	this.student = student;
    }

    
    public int getRegRequestid() {
		return regRequestid;
	}
    
    
    public boolean getApproved() {
    	return approved;
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
    	return "Registration request №" + regRequestid + 
    			"\nCourse: " + course.toString() + 
    			"\nStudent: " + student + 
    			"\nStatus: " + (approved ? "Approved" : "Not Approved");
    }

}