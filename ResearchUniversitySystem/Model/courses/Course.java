package courses;

import java.util.Vector;
import java.util.HashSet;
import java.util.HashMap;
import java.io.Serializable;

import enums.CourseType;
import enums.LessonType;
import enums.Semester;
import users.students.Student;
import users.employees.Teacher;

/**
 * @author eva
 * @author Muslik
 */

// add formula for calculating how many lessons are being held - add a system to calculate lessons

public class Course implements Serializable {
	
	private String courseID;
	private String courseName;
    private String description;
    private HashMap<LessonType, Integer> credits = new HashMap<>();   // its like (2/0/1 - 2 lectures, 0 labs, 1 practice)
    private HashSet<Course> prerequesites = new HashSet<>();
    private Vector<Lesson> courseLessons = new Vector<>();
    private Vector<Teacher> courseTeachers = new Vector<>();
    private Semester semester;
    private CourseType courseType;
    
    private HashMap<Student,Mark> gradeBook;

    public Course(String courseID, String courseName) {
    	this.courseID = courseID;
    	this.courseName = courseName;
    }
    
    public Course(String courseID, String courseName, HashSet<Course> prerequesites) {
    	this.courseID = courseID;
    	this.courseName = courseName;
    	this.prerequesites = prerequesites;
    }
    
    public boolean checkPrerequesites(Student student) {
        for (Course c : prerequesites) {
        	if (!student.getCompletedCourses().contains(c)) {
        		return false;
        	}
        }
        return true;
    }
    
    public String getID() {
    	return courseID;
    }

    public void addLesson(Lesson lesson) {
        // TODO implement here
        return;
    }

    public void removeLesson(Lesson lesson) {
        // TODO implement here
        return;
    }

    public HashMap<Student,Mark> getGradeBook() {
        // TODO implement here
        return null;
    }

    public GradeReport getGradeReport() {
        // TODO implement here
        return null;
    }

    public void addTeacherToCourse(Teacher teacher) {
    	courseTeachers.add(teacher);
    }

    public void assignRetake(CourseRepository cr, Student student) {
        // TODO implement here
        return;
    }

    public void assignMark(Student student, Mark mark) {
        // TODO implement here
        return;
    }
    
    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrerequesites(HashSet<Course> prerequesites) {
        this.prerequesites = prerequesites;
    }

    public void setCourseLessons(Vector<Lesson> courseLessons) {
        this.courseLessons = courseLessons;
    }

    public void setCourseTeachers(Vector<Teacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public void setCredits(LessonType type, int creditValue) {
        credits.put(type, creditValue);
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setGradeBook(HashMap<Student, Mark> gradeBook) {
        this.gradeBook = gradeBook;
    }

  
    public String toString() {
    	return "CourseID : " + courseID + " || courseName: " + courseName;
    }

}
