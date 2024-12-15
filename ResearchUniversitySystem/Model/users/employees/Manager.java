package users.employees;

import enums.Managers;
import menuInfo.NewsRepository;
import courses.Course;
import courses.CourseRepository;
import courses.TimeWindow;
import courses.RegistrationRequest;
import courses.CourseRegistrationService;
import users.students.Student;
import utilities.social.RequestRepository;
import utilities.social.Request;
import utilities.comparators.*;

/**
 * @author Muslik
 */
public class Manager extends Employee {
	
	private Managers managerType;

    public Manager(Managers managerType) {
    	this.managerType = managerType;
    }


    public void viewStudentsInfo() {
        // TODO implement here
        return ;
    }

    public void viewStudetnsInfo(StudentsComparator comp) {
        // TODO implement here
        return ;
    }

    public void viewTeachersInfo(Teacher teacher) {
        // TODO implement here
        return;
    }

    public void viewTeachersInfo(TeachersComparator comp) {
        // TODO implement here
        return ;
    }

    public void orderStudentsByGPA() {
        // TODO implement here
        return ;
    }


    public void orderStudentsAlphabetically() {
        // TODO implement here
        return ;
    }

    public void orderTeachersByRate() {
        // TODO implement here
        return ;
    }

    public void orderTeachersAlphabetically() {
        // TODO implement here
        return ;
    }

 
    public void postNews(NewsRepository newsRepo) {
        // TODO implement here
        return ;
    }

    public void createStatisticalReport() {
        // TODO implement here
        return ;
    }

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

    public void openRegistration(CourseRegistrationService crs) {
        crs.openRegistration();
    }
    
    public void closeRegistration(CourseRegistrationService crs) {
        crs.closeRegistration();
    }

    public void verifyRegistration(CourseRegistrationService crs, Integer id) {
    	RegistrationRequest request = crs.getRegRequest(id);
    	if(request.getCourse().checkPrerequesites(request.getStudent())) {
    		System.out.println("Succes! Registration was accepted by manager");
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


    public void putLessonTime(Course course, TimeWindow time) {
    	
        return ;
    }

    public void putLessonClassroom(Course course, int room) {
        // TODO implement here
        return ;
    }

}