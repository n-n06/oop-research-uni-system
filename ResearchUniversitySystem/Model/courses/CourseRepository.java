package courses;

import courses.Course;
import users.User;

import java.io.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.io.Serializable;


/**
 * @author Muslik
 */
public class CourseRepository implements Serializable {
	
	private HashMap<String, Course> courses;
	
    public CourseRepository() {
    	this.courses = new HashMap<>();
    }
    
    public void addCourse(Course course) {
        courses.put(course.getID(), course);
    }
    
    public boolean removeCourse(Course course) {
        return courses.remove(course.getID()) != null;
    }

    public Course getCourseByID(String courseID) {
        return courses.get(courseID);
    }
    
    public void displayCourses() {
    	System.out.println("Active courses to add:");
    	for (Entry<String, Course> entry : courses.entrySet()) {
    		System.out.println(entry.getValue());
    	}
    }

    public GradeReport getGradeReport(Course course) {
		return null;
    }

}