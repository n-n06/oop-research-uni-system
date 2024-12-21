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
 * @author eva
 */
public abstract class Student extends BaseUser {
	// 1. FIELDS
    private int year;
    private Degree degree;
    private School school;
    private int credits;
    // some field for gpa
    
    private Vector<StudentOrganization> studentOrganizations;
    private HashSet<Course> completedCourses = new HashSet<>();
    private HashSet<Course> currentCourses = new HashSet<>();
    
	// 2. CONSTRUCTORS: 
	public Student() {
		super();
	}

    public Student(String firstName, String lastName, int age, Gender gender, Degree degree, School school) {
    	super(firstName, lastName, age, gender);
    	this.degree = degree;
    	this.school = school;
    	this.year = 1; // by default, gonna have a method to increase a year for ALL students???? And if so we can have a Graduate() method? but it will be too overcomplicated... but then again will we need to handle transfer students??
    }

    // 3. WORKING WITH TEACHERS:
    public void rateTeacher(Teacher teacher, int rating) {
        teacher.getRatingMarks().add(rating);
    }
    
    // 4. WORKING WITH COURSES AND GRADES: --- NOT FINISHED, gprobably gonna separate it in sections for courses/lessons and for marks and att (if its imlemented here)
   
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
    
    public void getGPA() {
        // TODO implement here
        return ;
    }
    
    public void viewSchedule() {
        // TODO implement here
        return ;
    }
    
    public void addCourseToCompleted(Course course) {
    	completedCourses.add(course);
    }
    
    public HashSet<Course> getCompletedCourses() {
    	return completedCourses;
    }
    
    public void addCourse(Course course) {
    	currentCourses.add(course);
    	System.out.println("Course was added to Student's courses.");
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

    // 5. STUDENT ORGANIZATIONS:
    public StudentOrganization startOrganization(String name) {
        return new StudentOrganization(name, this);
    }
    
    public void joinOrganization(StudentOrganization org) {
        org.addStudent(this);
        this.studentOrganizations.add(org);
    }
    
    public void listAllOrganizations() {
    	studentOrganizations.stream().forEach(org -> System.out.println(org.getName()));
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
