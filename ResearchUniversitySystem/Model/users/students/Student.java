package users.students;

import java.util.*;

import users.BaseUser;
import users.employees.Teacher;

import enums.*;
import courses.*;
import database.Database;
import research.*;
import social.messages.Request;

/**
 *
 */
public abstract class Student extends BaseUser implements CanBecomeResearcher {
	// 1. FIELDS
    private int year;
    private Degree degree;
    private School school;
    private int credits;
    private boolean isResearcher;
  
    private Transcript transcript = new Transcript(this);
    private Vector<StudentOrganization> studentOrganizations;
    private HashSet<Course> completedCourses = new HashSet<>();
    private HashSet<Course> currentCourses = new HashSet<>();
    private Schedule schedule = new Schedule();
    
	// 2. CONSTRUCTORS AND BASE GETTERS: 
	public Student() {
		super();
	}

    public Student(String firstName, String lastName, int age, Gender gender, Degree degree, School school, int year) {
    	super(firstName, lastName, age, gender);
    	this.degree = degree;
    	this.school = school;
    	this.setYear(year);
    }
    
    public School getSchool() {
		return school;
	}
    
    public Degree getDegree() {
		return degree;
	}
    
    public int getCredits() {
		return credits;
	}
    
    public int getYear() {
		return year;
	}
    
    public void setYear(int year) {
    	if (year > 0 && year < 5) {
    		this.year = year;
    	}
	}

    // 3. WORKING WITH TEACHERS:
    public void rateTeacher(Teacher teacher, int rating) {
        teacher.getRatingMarks().add(rating);
    }
    
    // 4. WORKING WITH GRADES:
   
    public void viewTranscript() {
        transcript.displayTranscript();
    }
    
    public Transcript getTranscript() {
        return transcript;
    }
    
    public double getGPA() {
        return transcript.getOverallGPA();
    }    
    
    public void viewMarks(Course course) {
        Mark mark = course.getGradeBook().get(this);
        if (!mark.getFirstAttMarks().isEmpty()) {
        	System.out.println("FIRST ATTESTATION MARKS: ");
        	mark.getFirstAttMarks().stream().forEach(m -> System.out.println(m));
        } else {
        	System.out.println("No marks for FIRST attestation!");
        }
        if (!mark.getSecondAttMarks().isEmpty()) {
        	System.out.println("SECOND ATTESTATION MARKS: ");
        	mark.getSecondAttMarks().stream().forEach(m -> System.out.println(m));
        } else {
        	System.out.println("No marks for SECOND atttestation!");
        }
    }
    
    // 5. WORKING WITH COURSES & SCHEDULE:
    
    public void viewSchedule() {
        schedule.printSchedule();
    }
    
    public void addCourseToCompleted(Course course) {
    	completedCourses.add(course);
    }
    
    public HashSet<Course> getCompletedCourses() {
    	return completedCourses;
    }
    
    public HashSet<Course> getCurrentCourses() {
    	return currentCourses;
    }
    
    public void addCourse(Course course) {
    	currentCourses.add(course);
    	System.out.println("Course was added to Student's courses.");
    }
    
    public void registerForCourse(CourseRegistrationService crs, Course course) {
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
    
    public void displayLessonsForScheduling(Course course) {
    	course.viewLessonInSchedule();
    }
    
    public void displayLessonsForScheduling(Course course, Teacher teacher) {
    	course.viewLessonInSchedule();
    }
    
    public void pickLessonToSchedule(int index, Course course) {
    	Lesson lesson = course.getCourseSchedule().selectLessonByIndex(index);
    	if (lesson != null) {
    		schedule.addLesson(lesson);
    		course.getGradeBook().put(this, new Mark());
    	}
    	else {
    		System.out.println("Invalid selection. Please choose a valid index.");
    	}
    }

    // 6. STUDENT ORGANIZATIONS:
    public StudentOrganization startOrganization(String name) {
    	StudentOrganization newOrg = new StudentOrganization(name, this);
        this.studentOrganizations.add(newOrg);
        return newOrg;
    }
    
    public void joinOrganization(StudentOrganization org) {
        org.addStudent(this);
        this.studentOrganizations.add(org);
    }
    
    public void viewOrganzations() {
    	System.out.println("All Student organizations: \n");
    	Database.instance.getOrganizations().stream().forEach(o->System.out.println(o));
    }
    
    public void viewOwnOrganizations() {
    	studentOrganizations.stream().forEach(org -> System.out.println(org.getName()));
    }

    // 7. REQUESTS:
    public void sendRequest(Request request) {
    	Database.instance.getReqeustRepo().addRequest(school, request);
    }
   
    // 8. Diploma project
    //TODO: Research based - nurs
    public ResearchProject getDiplomaProject() {
        return null;
    }


    @Override
    public String toString() {
    	return super.toString() + "\nYear of study: " + this.year 
    			+ "\nGPA: " + this.getGPA() + "\nSchool: " + this.school.name()
    			+ "\nDegree: " + this.degree.name() + "\nCredits: " + this.credits;
    }
    
    @Override
    public void becomeResearcher() {
    	isResearcher = true;
    	Researcher r = new Researcher(this);
    }
    
    @Override
    public boolean getIsResearcher() {
    	return isResearcher;
    }
     
}
