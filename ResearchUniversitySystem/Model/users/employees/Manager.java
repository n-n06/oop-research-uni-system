package users.employees;

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


    public void viewStudentsInfo() {
        
    }

    public void viewStudentsInfo(Comparator<Student> comparator) {
    	List<Student> students = Database.instance.getUsersRepo().getAllStudents();
    	students.sort(comparator);
    	students.stream().forEach(s->System.out.println(s));
        //return ;
    }

    public void viewTeachersInfo(Teacher teacher) {
        // TODO implement here
        return;
    }

    public void viewTeachersInfo(Comparator<Teacher> comparator) {
    	List<Teacher> teachers = Database.instance.getUsersRepo().getAllTeachers();
    	teachers.sort(comparator);
    	teachers.stream().forEach(t->System.out.println(t));
        //return ;
    }
    
    
//    //This is probably not the best way to do it
//    public Vector<Student> orderStudentsByGPA(Vector<Student> students) {
//    	Vector<Student> sortedStudents = new Vector<>(students); 
//        sortedStudents.sort(new StudentGpaComparator());
//        return sortedStudents;
//    	//Vector<Student> students = orderStudentsByGPA(compare);
//    	//students.sort(new StudentGpaComparator());
//       // return students;
//    }
//
//
//    public Vector<Student> orderStudentsAlphabetically(Vector<Student> students) {
//    	Vector<Student> sortedStudents = new Vector<>(students); 
//        sortedStudents.sort(new StudentAlphabetComparator());
//        return sortedStudents;
//    }

//    public Vector<Teacher> orderTeachersByRate(Vector<Teacher> teachers) {
//    	Vector<Teacher> sortedTeachers = new Vector<>(teachers); 
//        sortedTeachers.sort(new TeacherRatingComparator());
//        return sortedTeachers;
//    }
//
//    public Vector<Teacher> orderTeachersAlphabetically(Vector<Teacher> teachers) {
//    	Vector<Teacher> sortedTeachers = new Vector<>(teachers); 
//        sortedTeachers.sort(new TeacherAlphabetComparator());
//        return sortedTeachers;
//    }

 
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


    public void addCourse(Course course) {
    	Database.instance.getCourseRepo().addCourse(course);
        //courseRepo.addCourse(course);
    }

    public void deleteCourse(Course course) {
    	Database.instance.getCourseRepo().removeCourse(course);
        //courseRepo.removeCourse(course);
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
    
    public void addLessonToCourse(Lesson lesson, Course course) {
    	course.addCourseLesson(lesson);
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

    
    //Course's Lesson management
    //Here we would have to make the checks for busy time windows
    public void putLessonTime(Course course, TimeWindow time) {
    	
        return ;
    }
    
    //Here we would have to make checks for busy classrooms
    public void putLessonClassroom(Course course, int room) {
        // TODO implement here
        return ;
    }
}