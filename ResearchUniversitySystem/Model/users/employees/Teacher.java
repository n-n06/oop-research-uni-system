import enums.TeacherType;
import social.messages.Complaint;
import users.employees.TeacherRating;

import java.io.*;
import java.util.*;

public class Teacher extends Employee {
	private TeacherType teacherType;
	private double rating;
	private Vector<Integer> ratingMarks = new Vector<>();
	
	HashSet<Course> assignedCourses = new HashSet<>();

    public Teacher() {
    	super();
    }
    public Teacher(String firstName, String lastName, String email, int age, Gender gender, TeacherType teacherType) {
    	super(firstName, lastName, email, age, gender);
    	this.teacherType = teacherType;
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
    
    	
    public void viewStudentList(Course coures) {
    	// TODO implement here
        return ;
    }

    public void viewStudentInfo(Course course, Student student) {
        System.out.println(student.toString());
        student.viewMarks(course);
    }

    public void sendComplaint(Complaint complaint) {
    	complaint.sendComplaint();
    }

    public void putMarkToStudent(Course course, Student s, Mark m) {
        course.assignMark(s, m);
    }

    public void putAttendance(Lesson lesson, Student student, Mark mark) {
        // TODO implement here
        return ;
    }

    public void checkRating() {
    	double rating = TeacherRating.getRating(this);
        System.out.println("Current average rating: " + rating);
    }

}
