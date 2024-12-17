package users.students;

import java.util.*;

import users.BaseUser;
import users.employees.Teacher;

import enums.*;
import courses.Course;
import courses.CourseRegistrationService;
import courses.Transcript;
import courses.GPA;
import courses.RegistrationRequest;
import research.ResearchProject;
import social.messages.RequestRepository;

/**
 * 
 */
public abstract class Student extends BaseUser {

    private int year;
    private Degree degree;
    private School school;
    private int credits;
    
    private Vector<StudentOrganization> studentOrganizations;
    private HashSet<Course> completedCourses = new HashSet<>();
    private HashSet<Course> currentCourses = new HashSet<>();
    
	
	public Student() {
		super();
	}

    public Student(String firstName, String lastName, String email, int age, Gender gender) {
    	super(firstName, lastName, email, age, gender);
    }
    
    
    
    public HashSet<Course> getCompletedCourses() {
    	return completedCourses;
    }

    public void viewTranscript() {
        // TODO implement here
        return ;
    }
    
    public Transcript getTranscript() {
        // TODO implement here
        return null;
    }

//    public void viewJournal() {
//        // TODO implement here
//        return ;
//    }


    public void makeRequest(RequestRepository reqRepo) {
        // TODO implement here
        return ;
    }

    public void viewMarks(Course course) {
        // TODO implement here
        return;
    }
    
    public GPA getGPA() {
    	// TODO implement here
    	return null;
    }
    
    public void viewSchedule() {
        // TODO implement here
        return ;
    }


    
    public void addCourseToCompleted(Course course) {
    	completedCourses.add(course);
    }
    
    public void addCourse(Course course) {
    	currentCourses.add(course);
    	System.out.println("Course was added to Student's courses");
    }
    
    public void registerForCourse(int id, CourseRegistrationService crs, Course course) {
    	RegistrationRequest rq = new RegistrationRequest(course, this);
        crs.addRegRequest(rq);
        System.out.println("Request for registration to " + course.getID());
    }
    
    public void displayOwnCourses() {
    	System.out.println("Student's active courses: ");
    	for (Course c : currentCourses) {
    		System.out.println(c);
    	}
    }

    //Teacher rating
    public void rateTeachers(Teacher teacher, int rating) {
        // TODO implement here
        return ;
    }

    //Student orgs
    public void startOrganization() {
        // TODO implement here
        return ;
    }
    
    public void joinOrganization() {
        // TODO implement here
        return ;
    }


    //TODO: Research based - nurs
    public ResearchProject getDiplomaProject() {
        return null;
    }


    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
     
    
    


}