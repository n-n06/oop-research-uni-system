package courses;

import java.util.Vector;
import java.util.HashSet;
import java.util.HashMap;

import enums.CourseType;
import enums.Semester;
import users.students.Student;
import users.employees.Teacher;

/**
 * @author eva
 */

// problem: cant have duplicate subjects (with the same id) but can have courses that belong to one subjects
// add credit system (2/0/1 - 2 lectures, 0 labs, 1 practice)
// add formula for calculating how many lessons are being held - add a system to calculate lessons
public class Course extends Subject {
	private String courseID;
	private static HashMap<String, String> courses = new HashMap<>(); 
    /**
     * Default constructor
     */
    public Course(String subjectID, String subjectName, int courseID) {
    	super(subjectID), subjectName);
    	this.courseID = subjectID + courseID;
    	addCourse(this);
    }
/*
    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private int credits;

    /**
     * 
     */
    private HashSet<Course> prerequesites;

    /**
     * 
     */
    private Vector<Lesson> courseLessons;

    /**
     * 
     */
    private Vector<Teacher> courseTeachers;

    /**
     * 
     */
    private Semester semester;

    /**
     * 
     */
    private CourseType courseType;

    /**
     * 
     */
    private HashMap<Student,Mark> gradeBook;

    /**
     * @param student 
     * @return
     */
    public boolean checkPrerequesites(Student student) {
        // TODO implement here
        return false;
    }

    /**
     * @param lesson 
     * @return
     */
    public void addLesson(Lesson lesson) {
        // TODO implement here
        return;
    }

    /**
     * @param lesson 
     * @return
     */
    public void removeLesson(Lesson lesson) {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public HashMap<Student,Mark> getGradeBook() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public GradeReport getGradeReport() {
        // TODO implement here
        return null;
    }

    /**
     * @param cr 
     * @param student 
     * @return
     */
    public void assignRetake(CourseRepository cr, Student student) {
        // TODO implement here
        return;
    }

    /**
     * @param student 
     * @param mark 
     * @return
     */
    public void assignMark(Student student, Mark mark) {
        // TODO implement here
        return;
    }
*/
}
