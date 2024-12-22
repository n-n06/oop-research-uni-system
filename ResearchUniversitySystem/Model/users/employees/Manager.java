package users.employees;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import courses.Course;
import courses.CourseRepository;
import courses.GradeReport;
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
import utilities.logging.LoggerProvider;

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
    	LoggerProvider.getLogger().info(getEmail() + " has posted news " + n.getTitle());
    	Database.instance.getNewsRepo().addNews(n);
    }

    public void createStatisticalReport() {
    	
    	Database.instance.getCourseRepo().getAllCourses().forEach(c -> {
    		GradeReport gr = c.getGradeReport();
    		gr.displayDistribution();
    		gr.displayReport();
    		gr.calculateGradeStatistics();
    		System.out.println(String.format("Max mark: %.2f\nMin mark: %.2f\nAverage mark: %.2f\n", gr.getMaxGrade(), gr.getMinGrade(), gr.getAverageGrade()));
    	});
        return ;
    }
    
    //Courses
    public void assignCourseToTeacher(Course course, Teacher teacher)  {
    	LoggerProvider.getLogger().info(getEmail() + " has assigned " + teacher.getEmail() + " as a teacher for " + course.getID());
        teacher.addCourse(course);
        course.addTeacherToCourse(teacher);
    }
    
    public void viewCourse(String id) {
    	Database.instance.getCourseRepo().getCourseByID(id);
    }

    public void addCourse(Course course) {
    	LoggerProvider.getLogger().info(getEmail() + " has added a new course " + course.getID() + " " + course.getCourseName());
    	Database.instance.getCourseRepo().addCourse(course);
    }

    public void deleteCourse(Course course) {
    	LoggerProvider.getLogger().warning(getEmail() + " has delete a course " + course.getID() + " " + course.getCourseName());
    	Database.instance.getCourseRepo().removeCourse(course);
    }
    
    
    //Registration
    public void openRegistration() {
    	LoggerProvider.getLogger().info(getEmail() + " has opened course registration");
    	Database.instance.getRegistration().openRegistration();
    }
    
    public void closeRegistration() {
    	LoggerProvider.getLogger().info(getEmail() + " has closed course registration");
    	Database.instance.getRegistration().closeRegistration();
    }

    public void verifyRegistration(Integer id) {
    	RegistrationRequest request = Database.instance.getRegistration().getRegRequest(id);
    	if(request.getCourse().checkPrerequesites(request.getStudent())) {
    		LoggerProvider.getLogger().info(getEmail() + " has verified regisration " + request.getRegRequestid() 
    		+ " for " + request.getCourse() 
    		+ " by " + request.getStudent().getEmail());
    		System.out.println("Success! Registration was accepted by manager");
    		request.getStudent().addCourse(request.getCourse());
    		request.setApproved(); 
    	} else {
    		LoggerProvider.getLogger().warning(getEmail() + " has declined regisration due to prerequisite " + request.getRegRequestid() 
    		+ " for " + request.getCourse() 
    		+ " by " + request.getStudent().getEmail());
    		System.out.println("Registration was declined by manager because prereq");
    	}
    }
    
    public void declineRegRequest(Integer id) {
    	Database.instance.getRegistration().getRegRequest(id);
    	RegistrationRequest request = Database.instance.getRegistration().getRegRequest(id);
		LoggerProvider.getLogger().warning(getEmail() + " has declined regisration due to prerequisite " + request.getRegRequestid() 
		+ " for " + request.getCourse() 
		+ " by " + request.getStudent().getEmail());
    	System.out.println("Registration was declined by manager");   
    }

    public void viewRegRequest(Integer id) {
        Database.instance.getRegistration().getRegRequest(id);;
    }
    
    public void viewAllRegRequest() {
    	Database.instance.getRegistration().displayRegRequests();
    }
    
    
    //Lesson management
    public void addLessonToCourse(Course course, int lessonRoom, LocalDate lessonDate, 
    		DayOfWeek dayOfWeek, TimeWindow lessonTime, LessonType lessonType, Teacher teacher) {
    	
    	course.fillLessons(lessonRoom, lessonDate, dayOfWeek, lessonTime, lessonType, teacher);
    	LoggerProvider.getLogger().info(getEmail() + " has added lessons for " + course.getID() 
    		+ " on " + dayOfWeek + " at "  + lessonTime);
    }
        
        
    public void changeLessonTimeInSchedule(Course course, Integer index, TimeWindow newTimeWindow) {
		LoggerProvider.getLogger().info(getEmail() + " has changed lesson number " + id + " time for course " + course.getID() 
		+ " to " + time);
        Lesson lessonToUpdate = course.getCourseSchedule().selectLessonByIndex(index);
        course.getCourseSchedule().updateLesson(lessonToUpdate.getDayOfWeeek(), lessonToUpdate.getLessonTime(), newTimeWindow);
        changeLessonTime(course, lessonToUpdate.getID(), newTimeWindow);
	}
    
    public void changeLessonTime(Course course, Integer lessonID, TimeWindow newTimeWindow) {
		LoggerProvider.getLogger().info(getEmail() + " has changed lesson number " + id + " time for course " + course.getID() 
		+ " to " + time);
    	Lesson lessonToUpdate = course.getLessonByID(lessonID).clone();
    	
    	for (Lesson lesson : course.getCourseLessons().values()) {
    		if (lesson.equals(lessonToUpdate)) {
    			lesson.setTime(newTimeWindow);
    		}
    	}
    	course.getCourseSchedule().updateLesson(lessonToUpdate.getDayOfWeeek(), lessonToUpdate.getLessonTime(), newTimeWindow);
    }
    

    
    //Requests - general ones
    public void viewRequest(Request request, School school) {
        System.out.println(Database.instance.getReqeustRepo().getRequest(school, request));
    }

    public void acceptRequest(Request request, School school) {
    	LoggerProvider.getLogger().info(getEmail() + " has aceepted request sent by " + request.getReceiver().getEmail() + " on " + request.getDate());
    	Database.instance.getReqeustRepo().acceptRequest(school, request);
    }


    public void declineRequest(Request request, School school) {
    	LoggerProvider.getLogger().warning(getEmail() + " has declined request sent by " + request.getReceiver().getEmail() + " on " + request.getDate());
    	Database.instance.getReqeustRepo().declineRequest(school, request);
    }


    
    @Override
    public String toString() {
    	return "👩‍💼Manager\n" + super.toString();
    }
}