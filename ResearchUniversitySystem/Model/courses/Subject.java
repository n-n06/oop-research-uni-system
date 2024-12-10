package courses;

import java.util.*;

/**
 * @author eva
 */

public class Subject {
    private String subjectID;
    private String subjectName;
    private static HashMap<String, HashMap<String, Vector<Course>>> subjects = new HashMap<>(); // ensuring we have unique subject IDs BUT that subject can have MULTIPLE courses

    /**
     * Default constructor
     */
    public Subject(String subjectID, String subjectName) {
    	this.subjectID = subjectID;
    	this.subjectName = subjectName;
    	
    	subjects.putIfAbsent(subjectID, new HashMap<>());
    	subjects.get(subjectID).putIfAbsent(subjectName, new Vector<>());
    	
    }
    
    public void addCourse(Course course) {
    	isCourseAdded(course);
    	subjects.get(subjectID).get(subjectName).add(course);
    }
    
    private boolean isCourseAdded(Course course) {
    	Vector<Course> courses = subjects.get(subjectID).get(subjectName);
    	return courses.contains(course); // to ensure there are no duplicate courses
    }

    /**
     * 
     */
    
    public String getID() {
    	return this.subjectID;
    }
    
    public String getName() {
    	return this.subjectName;
    }
    
    public Vector<Course> getCourses() {
    	return subjects.get(subjectID).get(subjectName);
    }

}
