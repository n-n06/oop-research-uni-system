package courses;

import java.util.*;
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
    private EnumMap<LessonType, Integer> credits = new EnumMap<>(LessonType.class);   // its like (2/0/1 - 2 lectures, 0 labs, 1 practice)
    private HashSet<Course> prerequesites = new HashSet<>();
    private HashMap<Integer, Lesson> courseLessons = new HashMap<>();
    private int lessonID;
    private Vector<Teacher> courseTeachers = new Vector<>();
    private Semester semester;
    private CourseType courseType;
    
    private HashMap<Student,Mark> gradeBook;

    {
    	lessonID = 0;
    }
    
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
    
    public Set getStudents() {
    	return gradeBook.keySet();
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
     
    public void addCourseLesson(Lesson lesson) {
    	courseLessons.put(generateLessonID(), lesson);
    }
    
    private Integer generateLessonID() {
		return lessonID++;
	}

	public HashMap<Integer, Lesson> getCourseLessons() {
    	return courseLessons;
    }
    
    
    public void viewLessons(Course course) {
    	for (Lesson lesson : courseLessons.values()) {
    		 System.out.println(lesson);
    	}
    }
    
    public void viewLessons(Course course, Teacher teacher) {
    	for (Lesson lesson : courseLessons.values()) {
    		if (lesson.getTeacher().equals(teacher)) {
    			System.out.println(lesson);
    		}
    	}
    }
    
    public void viewLessons(Course course, Date date) {
    	for (Lesson lesson : courseLessons.values()) {
    		if (lesson.getDate().equals(date)) {
    			System.out.println(lesson);
    		}
    	}
    }
     
    
    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrerequesites(HashSet<Course> prerequesites) {
        this.prerequesites = prerequesites;
    }

    public void setCourseLessons(HashMap<Integer, Lesson> courseLessons) {
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
    	return "Course ID : " + courseID + " || Course Name: " + courseName;
    }

	public String getCourseName() {
		return courseName;
	}


}
