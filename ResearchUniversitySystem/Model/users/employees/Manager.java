package users.employees;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import courses.Course;
import courses.CourseRepository;
import courses.Lesson;
import courses.TimeWindow;
import database.Database;
import courses.RegistrationRequest;
import courses.CourseRegistrationService;

import enums.*;
import social.*;
import social.messages.Request;
import social.messages.RequestRepository;
import social.updates.News;
import users.students.Student;
import utilities.comparators.*;

public class Manager extends Employee {
	private ManagerType managerType;
	public Manager() {
		
	}
	
    public Manager(String firstName, String lastName, int age, Gender gender, ManagerType managerType) {
    	super(firstName, lastName, age, gender);
    	this.managerType = managerType;
    }
    
    public void viewUserInfo(String email) {
    	System.out.println(Database.instance.getUsersRepo().getUser(email));
    }

    public void viewStudentsInfo() {
    	List<Student> students = Database.instance.getUsersRepo().getAllStudents();
    	System.out.println("All students: \n");
    	students.stream().forEach(s->System.out.println(s));
    }

    public void viewStudentsInfo(Comparator<Student> comparator) {
    	List<Student> students = Database.instance.getUsersRepo().getAllStudents();
    	students.sort(comparator);
    	students.stream().forEach(s->System.out.println(s));
    }

    public void viewTeachersInfo() {
    	List<Teacher> teachers = Database.instance.getUsersRepo().getAllTeachers();
    	System.out.println("All teachers: \n");
    	teachers.stream().forEach(t->System.out.println(t));
    }

    public void viewTeachersInfo(Comparator<Teacher> comparator) {
    	List<Teacher> teachers = Database.instance.getUsersRepo().getAllTeachers();
    	teachers.sort(comparator);
    	teachers.stream().forEach(t->System.out.println(t));
    }
    
    

 
    public void postNews(News n) {
    	Database.instance.getNewsRepo().addNews(n);
    }

    public void createStatisticalReport() {
        // TODO implement here
        return ;
    }
    
    //Courses
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        teacher.addCourse(course);
        course.addTeacherToCourse(teacher);
    }
    
    public void viewCourse(String id) {
    	Database.instance.getCourseRepo().getCourseByID(id);
    }

    public void addCourse(Course course) {
    	Database.instance.getCourseRepo().addCourse(course);
    }

    public void deleteCourse(Course course) {
    	Database.instance.getCourseRepo().removeCourse(course);
    }
    
    
    //Registration
    public void openRegistration(CourseRegistrationService crs) {
    	Database.instance.getRegistration().openRegistration();
        //crs.openRegistration();
    }
    
    public void closeRegistration(CourseRegistrationService crs) {
    	Database.instance.getRegistration().closeRegistration();
        //crs.closeRegistration();
    }

    public void verifyRegistration(CourseRegistrationService crs, Integer id) {
    	RegistrationRequest request = crs.getRegRequest(id);
    	if(request.getCourse().checkPrerequesites(request.getStudent())) {
    		System.out.println("Success! Registration was accepted by manager");
    		request.getStudent().addCourse(request.getCourse());
    		request.setApproved(); 
    	} else {
    		System.out.println("Registration was declined by manager because prereq");
    	}
    }
    
    public void declineRegRequest(Integer id) {
    	Database.instance.getRegistration().getRegRequest(id);
    	//RegistrationRequest request = crs.getRegRequest(id);
    	System.out.println("Registration was declined by manager");   
    }

    public void viewRegRequest(Integer id) {
        Database.instance.getRegistration().getRegRequest(id);
    	//System.out.println(crs.getRegRequest(id));
    }
    
    public void viewAllRegRequest() {
    	Database.instance.getRegistration().displayRegRequests();
        //crs.displayRegRequests();
    }
    
    
    //Lesson management
    public void addLessonToCourse(Course course, int lessonRoom, LocalDate lessonDate, 
    		DayOfWeek dayOfWeek, TimeWindow lessonTime, LessonType lessonType, Teacher teacher) {
    	
    	course.fillLessons(lessonRoom, lessonDate, dayOfWeek, lessonTime, lessonType, teacher);
    }
        
        
    public void putLessonTime(Course course, TimeWindow time) {
        return ;
    }
    
    //Here we would have to make checks for busy classrooms
    public void putLessonClassroom(Course course, int room) {
        // TODO implement here
        return ;
    }
    
    
    //Requests - general ones
    public void viewRequest(Request request, School school) {
        System.out.println(Database.instance.getReqeustRepo().getRequest(school, request));
    }

    public void acceptRequest(Request request, School school) {
    	Database.instance.getReqeustRepo().acceptRequest(school, request);
    }


    public void declineRequest(Request request, School school) {
    	Database.instance.getReqeustRepo().declineRequest(school, request);
    }


    
    @Override
    public String toString() {
    	return "👩‍💼Manager\n" + super.toString();
    }
}