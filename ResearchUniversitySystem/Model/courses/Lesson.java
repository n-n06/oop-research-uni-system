package courses;

import java.util.Date;
import java.util.HashMap;

import courses.TimeWindow;
import database.Database;
import enums.LessonType;

import users.employees.Teacher;
import users.students.Student;


public class Lesson {
	private int lessonID;
    public LessonType type;
    public Course course;
    public Teacher teacher;
    public Date lessonDate;
    private TimeWindow lessonTime;
    private int lessonRoom;
    private LessonType lessonType;
	
    private HashMap<Student, Boolean> attendanceList = new HashMap<>();
    private HashMap<Student, Double> markOfLesson = new HashMap<>();
    
   
    public Lesson(Course course, int lessonRoom, TimeWindow lessonTime, LessonType lessonType) {
    	this.course = course;
    	this.lessonRoom = lessonRoom;
    	this.lessonTime = lessonTime;
    	this.lessonType = lessonType;
    }
    
    public Lesson(Course course, int lessonRoom, TimeWindow lessonTime, LessonType lessonType, Teacher teacher) {
    	this(course, lessonRoom, lessonTime, lessonType);
    	this.teacher = teacher;
    }
    
    public void assignTeacher(Teacher newTeacher) { 
    	this.teacher = newTeacher;
    }
    
    public String toString() {
        return this.course.getCourseName() + " "
        	   + (this.teacher != null ? this.teacher.getLastName() + " " + this.teacher.getFirstName().charAt(0) + "." : "Vacancy") + " "
               + this.lessonType + " "
               + this.lessonRoom
               + " " + this.lessonTime;
    }
    
    public void putAttendance(Student student, Boolean isPresent) {
    	attendanceList.put(student, isPresent);
    }
    
    public void putMark(Student student, Double point) {
    	markOfLesson.put(student, point);
    }

	public Integer getID() {
		return lessonID;
	}
	
	public void setID(int ID) {
		this.lessonID = ID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Date getDate() {
		return lessonDate;
	}

}
