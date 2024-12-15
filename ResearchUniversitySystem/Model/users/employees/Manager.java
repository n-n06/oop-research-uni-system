package users.employees;

import enums.Managers;
import menuInfo.NewsRepository;
import courses.Course;
import courses.CourseRepository;
import courses.TimeWindow;
import courses.CourseRegistrationService;
import users.students.Student;
import utilities.social.RequestRepository;
import utilities.social.Request;
import utilities.comparators.*;

public class Manager extends Employee {

    /**
     * Default constructor
     */
    public Manager() {
    }

    private Managers managerType;

    public void viewStudentsInfo() {
        // TODO implement here
        return ;
    }

    /**
     * @param comp 
     * @return
     */
    public void viewStudetnsInfo(StudentsComparator comp) {
        // TODO implement here
        return ;
    }

    /**
     * @param teacher 
     * @return
     */
    public void viewTeachersInfo(Teacher teacher) {
        // TODO implement here
        return;
    }

    /**
     * @param comp 
     * @return
     */
    public void viewTeachersInfo(TeachersComparator comp) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void orderStudentsByGPA() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void orderStudentsAlphabetically() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void orderTeachersByRate() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void orderTeachersAlphabetically() {
        // TODO implement here
        return ;
    }

    /**
     * @param newsRepo 
     * @return
     */
    public void postNews(NewsRepository newsRepo) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void createStatisticalReport() {
        // TODO implement here
        return ;
    }

    /**
     * @param course 
     * @param teacher 
     * @return
     */
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        // TODO implement here
        return ;
    }

    /**
     * @param courseRepo 
     * @return
     */
    public void addCourse(CourseRepository courseRepo) {
        // TODO implement here
        return ;
    }

    /**
     * @param courseRepo 
     * @return
     */
    public void deleteCourse(CourseRepository courseRepo) {
        // TODO implement here
        return ;
    }

    /**
     * @param crs 
     * @return
     */
    public void openRegistration(CourseRegistrationService crs) {
        // TODO implement here
        return ;
    }

    /**
     * @param crs 
     * @param student 
     * @return
     */
    public boolean verifyRegistration(CourseRegistrationService crs, Student student) {
        // TODO implement here
        return false;
    }

    /**
     * @param crs 
     * @return
     */
    public void viewRegistration(CourseRegistrationService crs) {
        // TODO implement here
        return ;
    }

    /**
     * @param crs 
     * @param student 
     * @return
     */
    public void viewStudentRegistration(CourseRegistrationService crs, Student student) {
        // TODO implement here
        return ;
    }

    /**
     * @param crs 
     * @param student 
     * @return
     */
    public void cancelRegistration(CourseRegistrationService crs, Student student) {
        // TODO implement here
        return ;
    }

    /**
     * @param requestRepo 
     * @return
     */
    public void viewRequest(RequestRepository requestRepo) {
        // TODO implement here
        return ;
    }

    /**
     * @param request 
     * @return
     */
    public void acceptRequest(Request request) {
        // TODO implement here
        return ;
    }

    /**
     * @param request 
     * @return
     */
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