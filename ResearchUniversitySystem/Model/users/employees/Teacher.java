package users.employees;

import java.util.*;

import enums.*;
import social.messages.Complaint;
import users.students.*;
import courses.*;
import database.Database;

public class Teacher extends Employee {
	private TeacherType teacherType;
	private School school;
	private double rating;
	private Vector<Integer> ratingMarks = new Vector<>();
	
	HashSet<Course> assignedCourses = new HashSet<>();
	

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
    	TeacherRating.addTeacherToRating(this);//changed addTeacherToRating into 'static' or create new instance of teacher everytime
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
    
    public void addCourse(Course course) {
    	assignedCourses.add(course);
    }
    
    public void viewOwnCourses() {
    	System.out.println("Teacher's courses:");
    	for (Course c : assignedCourses) {
    		System.out.println(c);
    	}
    }
    
    public void viewOwnLessons(Course course) {
    	course.viewLessons(course, this);
    }
    	
    public void viewStudentList(Course course) {
    	System.out.println(course.getCourseName() + " students:");
    	course.getStudents().stream().forEach(s->System.out.println(s));
    }

    public void viewStudentInfo(Course course, Student student) {
        System.out.println(student.toString());
        student.viewMarks(course);
    }

    public void sendComplaint(Complaint complaint) {
    	Database.instance.getComplaints().add(complaint);
    }

    public void putMarkToStudent(Course course, Student s, Mark m) {
        course.assignMark(s, m);
    }

    public void putAttendance(Lesson lesson, Student student, Mark mark) {
        return ;
    }

    public void checkRating() {
    	double rating = TeacherRating.getRating(this);
        System.out.println("Current average rating: " + rating);
    }

}
