package users.employees;

import java.util.Comparator;
import java.util.Vector;

import courses.Course;
import courses.CourseRepository;
import courses.TimeWindow;
import database.Database;
import courses.RegistrationRequest;
import courses.CourseRegistrationService;

import enums.*;

import menuInfo.*;

import users.students.Student;

import utilities.social.RequestRepository;
import utilities.social.Request;
import utilities.comparators.*;

/**
 * @author Muslik
 */
public class Manager extends Employee {
	
	private Managers managerType;

	public Manager() {
		
	}
    public Manager(String firstName, String lastName, String email, int age, Gender gender, Managers managerType) {
    	super(firstName, lastName, email, age, gender);
    	this.managerType = managerType;
    }


    public void viewStudentsInfo() {
        ;
    }

    public void viewStudentsInfo(Comparator<Student> comparator) {
        return ;
    }

    public void viewTeachersInfo(Teacher teacher) {
        // TODO implement here
        return;
    }

    public void viewTeachersInfo(Comparator<Teacher> comparator) {
        // TODO implement here
        return ;
    }
    
    
    //This is probably not the best way to do it
    public Vector<Student> orderStudentsByGPA(Vector<Student> students) {
    	Vector<Student> sortedStudents = new Vector<>(students); 
        sortedStudents.sort(new StudentGpaComparator());
        return sortedStudents;
    	//Vector<Student> students = orderStudentsByGPA(compare);
    	//students.sort(new StudentGpaComparator());
       // return students;
    }


    public Vector<Student> orderStudentsAlphabetically(Vector<Student> students) {
    	Vector<Student> sortedStudents = new Vector<>(students); 
        sortedStudents.sort(new StudentAlphabetComparator());
        return sortedStudents;
    }

    public Vector<Teacher> orderTeachersByRate(Vector<Teacher> teachers) {
    	Vector<Teacher> sortedTeachers = new Vector<>(teachers); 
        sortedTeachers.sort(new TeacherRatingComparator());
        return sortedTeachers;
    }

    public Vector<Teacher> orderTeachersAlphabetically(Vector<Teacher> teachers) {
    	Vector<Teacher> sortedTeachers = new Vector<>(teachers); 
        sortedTeachers.sort(new TeacherAlphabetComparator());
        return sortedTeachers;
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


    public void addCourse(CourseRepository courseRepo, Course course) {
        courseRepo.addCourse(course);
    }

    public void deleteCourse(CourseRepository courseRepo, Course course) {
        courseRepo.removeCourse(course);
    }
    
    
    //Registration
    public void openRegistration(CourseRegistrationService crs) {
        crs.openRegistration();
    }
    
    public void closeRegistration(CourseRegistrationService crs) {
        crs.closeRegistration();
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
    
    public void declineRegRequest(CourseRegistrationService crs, Integer id) {
    	RegistrationRequest request = crs.getRegRequest(id);
    	System.out.println("Registration was declined by manager");   
    }

    public void viewRegRequest(CourseRegistrationService crs, Integer id) {
        System.out.println(crs.getRegRequest(id));
    }
    
    public void viewAllRegRequest(CourseRegistrationService crs) {
        crs.displayRegRequests();
    }
    

    //Requests - general ones
    public void viewRequest(RequestRepository requestRepo) {
        // TODO implement here
        return ;
    }

    public void acceptRequest(Request request) {
        // TODO implement here
        return ;
    }


    public void declineRequest(Request request) {
        // TODO implement here
        return ;
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
