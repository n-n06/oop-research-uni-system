package courses;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    public LocalDate lessonDate;
    private TimeWindow lessonTime;
    private int lessonRoom;
    private LessonType lessonType;
	
    private HashMap<Student, Boolean> attendanceList = new HashMap<>();
    private HashMap<Student, Double> marksOfLesson = new HashMap<>();
    
   
    public Lesson(Course course, int lessonRoom, LocalDate lessonDate, TimeWindow lessonTime, LessonType lessonType) {
    	this.course = course;
    	this.lessonRoom = lessonRoom;
    	this.lessonDate = lessonDate;
    	this.lessonTime = lessonTime;
    	this.lessonType = lessonType;
    }
    
    public Lesson(Course course, int lessonRoom, LocalDate lessonDate, TimeWindow lessonTime,  LessonType lessonType, Teacher teacher) {
    	this(course, lessonRoom, lessonDate, lessonTime, lessonType);
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
    
    public void putMark(Student student, Double point, int attestation) {
    	marksOfLesson.put(student, point);
//    	course.addMarkForStudent(student, point, attestation);    /// IT WILL WORK WHEN STUDENT WILL PICK LESSONS TO SCHEDULE
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

	public LocalDate getDate() {
		return lessonDate;
	}
	
	
    public void viewAttendanceList() {
        if (attendanceList.isEmpty()) {
            System.out.println("Attendance list is empty.");
        } else {
            System.out.println("Attendance List of lesson: ID" + this.getID());
            for (Map.Entry<Student, Boolean> entry : attendanceList.entrySet()) {
                System.out.println(entry.getKey().getFullName() + " - Present: " + entry.getValue());
            }
        }
    }

    public void viewMarksOfLesson() {
        if (marksOfLesson.isEmpty()) {
            System.out.println("Marks list is empty.");
        } else {
            System.out.println("Marks of Lesson: ID" + this.getID());
            for (Map.Entry<Student, Double> entry : marksOfLesson.entrySet()) {
                System.out.println(entry.getKey().getFullName() + " - Mark: " + entry.getValue());
            }
        }
    }

}
