package courses;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import courses.TimeWindow;
import database.Database;
import enums.LessonType;

import users.employees.Teacher;
import users.students.Student;


public class Lesson implements Cloneable, Serializable {
	private int lessonID;
    public Course course;
    public Teacher teacher;
    public LocalDate lessonDate;
    private DayOfWeek dayOfWeek;
    private TimeWindow lessonTime;
    private int lessonRoom;
    private LessonType lessonType;
	
    private HashMap<Student, Boolean> attendanceList = new HashMap<>();
    private HashMap<Student, Double> marksOfLesson = new HashMap<>();
    
   
    public Lesson(Course course, int lessonRoom, LocalDate lessonDate, DayOfWeek dayOfWeek, TimeWindow lessonTime, LessonType lessonType) {
    	this.course = course;
    	this.lessonRoom = lessonRoom;
    	this.lessonDate = lessonDate;
    	this.dayOfWeek = dayOfWeek;
    	this.lessonTime = lessonTime;
    	this.lessonType = lessonType;
        for (Student s : course.getGradeBook().keySet()) {
            marksOfLesson.put(s, 0.0);
        }
        for (Student s : course.getGradeBook().keySet()) {
            attendanceList.put(s, false);
        } 
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
    	course.addMarkForStudent(student, point, attestation);
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
		return dayOfWeek;
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
	
	
	public void setID(int ID) {
		this.lessonID = ID;
	}
	
	public void setTime(TimeWindow lessonTime) {
		this.lessonTime = lessonTime;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return lessonRoom == lesson.lessonRoom &&
               course.equals(lesson.course) &&
               teacher.equals(lesson.getTeacher()) &&
               dayOfWeek.equals(lesson.dayOfWeek) &&
               lessonType.equals(lesson.getLessonType()) &&
               lessonTime.equals(lesson.getLessonTime());
    }
    
    @Override
    public Lesson clone() {
        try {
            Lesson clonedLesson = (Lesson) super.clone();

            clonedLesson.course = this.course;  
            clonedLesson.teacher = this.teacher;  
            clonedLesson.lessonDate = this.lessonDate;
            clonedLesson.dayOfWeek = this.dayOfWeek;
            clonedLesson.lessonTime = this.lessonTime;  
            clonedLesson.lessonRoom = this.lessonRoom;
            clonedLesson.lessonType = this.lessonType;

            clonedLesson.attendanceList = this.attendanceList;
            clonedLesson.marksOfLesson = this.marksOfLesson;

            return clonedLesson;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, teacher, dayOfWeek, lessonTime, lessonRoom, lessonType, lessonTime);
    }
    
    public String toString() {
        return  this.course.getCourseName() + " "
        	   + (this.teacher != null ? this.teacher.getLastName() + " " + this.teacher.getFirstName().charAt(0) + "." : "Vacancy") + " "
               + this.lessonType + " "
               + this.lessonRoom
               + " " + this.lessonTime;
    }



}
