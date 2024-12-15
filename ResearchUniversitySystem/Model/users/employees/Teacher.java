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

public class Teacher extends Employee {
	private TeacherType teacherType;

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
    public void viewCourse(Course course) {
    	
    }

    public void viewOwnCourses() {
        TreeSet<Course> ownCourses = Database.instance.getCourseRepo().getCoursesOfAUser(this);
        if(ownCourses == null || ownCourses.isEmpty()) {
        	System.out.println("No courses assigned to "+ getName());
        } else {
        	for (Course c : ownCourses) {
        		System.out.println(c);
        	}
        }
        	
    }
    public void viewStudentList(Course coures) {
        
        return ;
    }

    public void viewStudentInfo(Course course, Student student) {
        System.out.println(student.toString());
        student.viewMarks(course);
    }

    public void sendComplaint(Complaint complaint) {
    	complaint.sendComplaint();
    }

    public void putMarkToStudent(Course c, Student s, Mark m) {
        c.assignMark(s, m);
    }

    public void putAttendance(Lesson l, Student s, Mark m) {
        // TODO implement here
        return ;
    }

    public void checkRating() {
    	double rating = TeacherRating.getRating(this);
        System.out.println("Current average rating: " + rating);
    }

}