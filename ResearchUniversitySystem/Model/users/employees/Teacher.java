package users.employees;

import users.students.Student;
import courses.*;
import database.Database;
import enums.Gender;
import enums.TeacherType;
import utilities.social.Complaint;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Teacher extends Employee {
	private double rating;
	private TeacherType teacherType;

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
    	
    }

    public void putMarkToStudent(Course c, Student s, Mark m) {
        c.assignMark(s, m);
    }

    public void putAttendance(Lesson l, Student s, Mark m) {
        // TODO implement here
        return ;
    }
    //

    /**
     * @param course 
     * @param time 
     * @return
     */
    public void putLessonTime(Course course, TimeWindow time) {
        // TODO implement here
        return ;
    }

    /**
     * @param course 
     * @param room 
     * @return
     */
    public void putLessonClassroom(Course course, int room) {
        // TODO implement here
        return ;
    }

    public void checkRating() {
        // TODO implement here
        return ;
    }

}