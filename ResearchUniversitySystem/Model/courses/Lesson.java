package courses;

import java.util.Date;
import java.util.HashMap;

import courses.TimeWindow;
import database.Database;
import enums.LessonType;

import users.employees.Teacher;
import users.students.Student;

// need to ensure one teacher can have only one lesson at time
// need to ensure we have two lectures that go together as a two pack
// need to ensure we cant have two lessons in the same room (maybe having a vector with room nums that are already assigned in the specific timewindow)
// need to ensure a student cant have two lessons at the same time 
// mark attendance
// have a single grade for a student per lesson

/**
 * @author eva
 */
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
    
    public void assignTeacher(Teacher newTeacher) { // because we often have 'Вакансия ағай'
    	this.teacher = newTeacher;
    }
    
    public String toString() {
        return this.course.getCourseName() + " "
        	   + (this.teacher != null ? this.teacher.getLastName() + " " + this.teacher.getFirstName().charAt(0) + "." : "Вакансия агай") + " "
               + this.lessonType + " "
               + this.lessonRoom
               + " " + this.lessonTime;
    }
    
    public void putAttendance(Student student, Boolean isPresent) {
    	attendanceList.put(student, isPresent);
    }

	public Integer getID() {
		return lessonID;
	}
	
	public void setID(int ID) {
		this.lessonID = ID;
	}

	public Object getTeacher() {
		return teacher;
	}

	public Date getDate() {
		return lessonDate;
	}

}
