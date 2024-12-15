package users.employees;

import users.students.Student;
import courses.*;
import database.Database;
import enums.Gender;
import enums.TeacherType;
import utilities.social.Complaint;
import users.employees.TeacherRating;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Teacher extends Employee {
	private double rating;
	private TeacherType teacherType;
	
	HashSet<Course> assignedCourses = new HashSet<>();

    public Teacher() {
    	super();
    }
    public Teacher(String firstName, String lastName, String email, int age, Gender gender, double rating, TeacherType teacherType) {
    	super(firstName, lastName, email, age, gender);
    	this.rating = rating;
    	this.teacherType = teacherType;
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
    //

    public void putLessonTime(Course course, TimeWindow time) {
        return ;
    }

    public void putLessonClassroom(Course course, int room) {
        // TODO implement here
        return ;
    }

    public void checkRating() {
    	double rating = TeacherRating.getRating(this);
        System.out.println("Current average rating: " + rating);
    }

}