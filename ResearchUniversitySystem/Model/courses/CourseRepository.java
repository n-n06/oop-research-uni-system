package courses;

import courses.Course;
import users.User;

import java.io.*;
import java.util.Vector;
import java.util.TreeSet;

/**
 * 
 */
public class CourseRepository {

    /**
     * Default constructor
     */
    public CourseRepository() {
    }

    /**
     * 
     */
    private TreeSet<Course> courses;

    /**
     * 
     */
    private double creditPrice;

    /**
     * @param course 
     * @return
     */
    public void addCourse(Course course) {
        // TODO implement here
        return;
    }

    /**
     * @param course 
     * @return
     */
    public boolean removeCourse(Course course) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void displayCourses() {
        // TODO implement here
        return;
    }

    /**
     * @param course 
     * @return
     */
    public Course getCourse(Course course) {
        // TODO implement here
        return null;
    }

    /**
     * @param user 
     * @return
     */
    public TreeSet<Course> getCoursesOfAUser(User user) {
        // TODO implement here
        return null;
    }

    /**
     * @param courses 
     * @return
     */
    public GradeReport getGradeReport(TreeSet<Course> courses) {
        // TODO implement here
        return null;
    }

}