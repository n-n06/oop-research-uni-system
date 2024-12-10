package users.employees;

import users.students.Student;
import courses.*;
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
    /**
     * Default constructor
     */
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
        System.out.println
    }

    /**
     * @return
     */
    public void viewOwnCourses() {
        TreeSet<Course> ownCourses = CourseRepository.getCoursesOfAUser(this);
        if(ownCourses == null || ownCourses.isEmpty()) {
        	System.out.println("No courses assigned to "+getName());
        	return;
        }
        	
    }

    /**
     * @param coures 
     * @return
     */
    public void viewStudentList(Course coures) {?????
        // TODO implement here
        return ;
    }

    /**
     * @param student 
     * @return
     */
    public void viewStudentInfo(Student student) {
        // TODO implement here
        return ;
    }

    /**
     * @param complaint 
     * @return
     */
    public void sendComplaint(Complaint complaint) {
        // TODO implement here
        return ;
    }

    public void putMarkToStudent(Course c, Student s, Mark m) {
        c.assignMark(s, m);
    }

    public void putAttendance(Lesson l, Student s, Mark m) {
        // TODO implement here
        return ;
    }

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