package courses;

import java.time.DayOfWeek;
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
    public Course course;
    public Teacher teacher;
    public LocalDate lessonDate;
    private DayOfWeek dayOfWeeek;
    private TimeWindow lessonTime;
    private int lessonRoom;
    private LessonType lessonType;
	
    private HashMap<Student, Boolean> attendanceList = new HashMap<>();
    private HashMap<Student, Double> marksOfLesson = new HashMap<>();
    
   
    public Lesson(Course course, int lessonRoom, LocalDate lessonDate, DayOfWeek dayOfWeek, TimeWindow lessonTime, LessonType lessonType) {
    	this.course = course;
    	this.lessonRoom = lessonRoom;
    	this.lessonDate = lessonDate;
    	this.dayOfWeeek = dayOfWeek;
    	this.lessonTime = lessonTime;
    	this.lessonType = lessonType;
    }
    
    public Lesson(Course course, int lessonRoom, LocalDate lessonDate, DayOfWeek dayOfWeek, TimeWindow lessonTime,  LessonType lessonType, Teacher teacher) {
    	this(course, lessonRoom, lessonDate, dayOfWeek, lessonTime, lessonType);
    	this.teacher = teacher;
    }
    
    public void assignTeacher(Teacher newTeacher) { 
    	this.teacher = newTeacher;
    }
    
    public void putAttendance(Student student, Boolean isPresent) {
    	attendanceList.put(student, isPresent);
    }
    
    public void putMark(Student student, Double point, int attestation) {
    	marksOfLesson.put(student, point);
//    	course.addMarkForStudent(student, point, attestation);    /// IT WILL WORK WHEN STUDENT WILL PICK LESSONS TO SCHEDULE
    }
   
    
	// Views
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

    // Checkers
	public Boolean checkStudentAttendance(Student student) {
		 return attendanceList.get(student);
	}
	
	public Double checkStudentMark(Student student) {
		 return marksOfLesson.get(student);
	}
	
	// Getters
	public Integer getID() {
		return lessonID;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public int getLessonRoom() {
		return lessonRoom;
	}

	public LocalDate getDate() {
		return lessonDate;
	}
	
	public DayOfWeek getDayOfWeeek() {
		return dayOfWeeek;
	}

	public TimeWindow getLessonTime() {
		return lessonTime;
	}
	
	public LocalDate getLessonDate() {
		return lessonDate;
	}
	
	public Course getLessonCourse() {
		return course;
	}
	
    public HashMap<Student, Boolean> getAttendanceList() {
    	return attendanceList;
    }
    
    public HashMap<Student, Double> getMarksOfLesson() {
    	return marksOfLesson;
    }
    
	public LessonType getLessonType() {
		return lessonType;
	}
	
    // Setters 
	public void setID(int ID) {
		this.lessonID = ID;
	}

    
    public String toString() {
        return this.course.getCourseName() + " "
        	   + (this.teacher != null ? this.teacher.getLastName() + " " + this.teacher.getFirstName().charAt(0) + "." : "Vacancy") + " "
               + this.lessonType + " "
               + this.lessonRoom
               + " " + this.lessonTime;
    }



}
