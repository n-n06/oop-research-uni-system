package courses;

import java.util.Vector;
import java.util.HashSet;
import java.util.HashMap;

import enums.CourseType;
import enums.Semester;
import users.students.Student;
import users.employees.Teacher;

/**
 * 
 */
public class Course extends Subject {

    /**
     * Default constructor
     */
    public Course() {
    }

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

}