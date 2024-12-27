package courses;

import courses.Course;
import users.User;

import java.io.*;
import java.util.Vector;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.io.Serializable;


/**
 * @author Muslik
 */
public class CourseRepository implements Serializable {
	
	private HashMap<String, Course> courses;
	private Vector<Course> courseBackup;
	
    public CourseRepository() {
    	this.courses = new HashMap<>();
    	courseBackup = new Vector<>();
    }
    
    public void addCourse(Course course) {
        courses.put(course.getID(), course);
        courseBackup.add(course);
    }
    
    public boolean removeCourse(Course course) {
        return courses.remove(course.getID()) != null;
    }

    public Course getCourseByID(String courseID) {
        return courses.get(courseID);
    }
    
    public Collection<Course> getAllCourses() {
    	return courses.values();
    }
    
    public void displayCourses() {
    	System.out.println("Active courses to add:");
    	for (Course c: courseBackup) {
    		System.out.println(c);
    	}
    }

    public GradeReport getGradeReport(Course course) {
		return null;
    }

}