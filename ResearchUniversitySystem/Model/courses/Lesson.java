package courses;

import java.util.Date;

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
	
    
    {
//    	lessonID = Database.generateLessonId();
    } 
    
    public Lesson(int id, Course course, int lessonRoom, TimeWindow lessonTime, LessonType lessonType) {
    	this.lessonID = id;
    	this.course = course;
    	this.lessonRoom = lessonRoom;
    	this.lessonTime = lessonTime;
    	this.lessonType = lessonType;

    }
    
    public Lesson(int id, Course course, int lessonRoom, TimeWindow lessonTime, LessonType lessonType, Teacher teacher) {
    	this(id, course, lessonRoom, lessonTime, lessonType);
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

	public Integer getID() {
		return lessonID;
	}

	public Object getTeacher() {
		return teacher;
	}

	public Date getDate() {
		return lessonDate;
	}

}
