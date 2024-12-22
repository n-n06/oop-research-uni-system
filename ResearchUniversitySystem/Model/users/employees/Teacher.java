package users.employees;

import java.util.*;

import enums.*;
import research.CanBecomeResearcher;
import research.Researcher;
import social.messages.*;
import users.students.*;
import courses.*;
import database.Database;

public class Teacher extends Employee implements CanBecomeResearcher {
	private TeacherType teacherType;
	private School school;
	private Vector<Integer> ratingMarks = new Vector<>();
	private boolean isResearcher = false;
	
	HashSet<Course> assignedCourses = new HashSet<>();
	private Schedule schedule = new Schedule();
	

    public Teacher() {
    	super();
    }
    
    public Teacher(String firstName, String lastName, int age, Gender gender, TeacherType teacherType, School school) {
    	super(firstName, lastName, age, gender);
    	this.teacherType = teacherType;
    	this.school = school;
    	if(teacherType == TeacherType.PROFESSOR) {
    		becomeResearcher();
    	}
    	TeacherRating.addTeacherToRating(this);
    }
    
    public void setTeacherType(TeacherType teacherType) {
    	this.teacherType = teacherType;
    }
    
    public TeacherType getTeacherType() {
    	return teacherType;
    }
    
    public Vector<Integer> getRatingMarks() {
    	return ratingMarks;
    }
    
    public Schedule getSchedule() {
		return schedule;
	}

    public HashSet<Course> getAssignedCourses() {
		return assignedCourses;
	}
    
    public School getSchool() {
		return school;
	}
    
    //Courses 
    public void addCourse(Course course) {
    	assignedCourses.add(course);
    }
    
    public void viewOwnCourses() {
    	System.out.println("Teacher's courses:");
    	for (Course c : assignedCourses) {
    		System.out.println(c);
    	}
    }
    
    //Lesson 
    public void viewOwnLessons(Course course) {
    	course.viewLessons(this);
    }
    	
    public void viewStudentList(Course course) {
    	System.out.println(course.getCourseName() + " students:");
    	course.getStudents().stream().forEach(s->System.out.println(s));
    }

    public void viewStudentInfo(Course course, Student student) {
        System.out.println(student.toString());
        student.viewMarks(course);
    }


    
    //Marks
    public void putMarkToLesson(Lesson lesson, Student student, Double point, int attestation) {
        lesson.putMark(student, point, attestation);
    }

    public void putAttendance(Lesson lesson, Student student, Boolean isPresent) {
        lesson.putAttendance(student, isPresent);
    }

    public void checkRating() {
    	double rating = TeacherRating.getRating(this);
        System.out.println("Current average rating: " + rating);
    }
    
    //Social
    public void sendComplaint(Complaint complaint) {
    	Database.instance.getComplaints().add(complaint);
    }
    
    public void sendRequest(Request request) {
    	Database.instance.getReqeustRepo().addRequest(school, request);
    }
    
    
    //Researcher
	@Override
	public void becomeResearcher() {
		isResearcher = true;
		Researcher r = new Researcher(this);
	}

	@Override
	public boolean getIsResearcher() {
		return isResearcher;
	}
	
	@Override
	public String toString() {
		return "👩‍🏫 Teacher" 
				+ super.toString()
				+ "\nTeacher type: " + teacherType.toString().toLowerCase().replace("_", " ")
				+ "\nSchool" + school ;
	}

}
