package users.students;

import java.util.*;

import users.BaseUser;
import users.employees.Teacher;

import enums.Degree;
import enums.School;

import utilities.social.RequestRepository;

import courses.Course;
import courses.Transcript;

import research.ResearchProject;

/**
 * 
 */
public abstract class Student extends BaseUser {

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * 
     */
    private int year;

    /**
     * 
     */
    private Degree degree;

    /**
     * 
     */
    private School school;

    /**
     * 
     */
    private int credits;

    /**
     * 
     */
    private Vector<StudentOrganization> studentOrganizations;

    /**
     * 
     */
    public HashSet<Course> completedCourses;

    /**
     * @return
     */
    public void viewTranscript() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void viewJournal() {
        // TODO implement here
        return ;
    }

    /**
     * @param reqRepo 
     * @return
     */
    public void makeRequest(RequestRepository reqRepo) {
        // TODO implement here
        return ;
    }

    /**
     * @param course 
     * @return
     */
    public void viewMarks(Course course) {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public Transcript getTranscript() {
        // TODO implement here
        return null;
    }

    /**
     * @param teacher 
     * @param int rating 
     * @return
     */
    public void rateTeachers(Teacher teacher, int rating) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void joinOrganization() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void startOrganization() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void viewCourses() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public ResearchProject getDiplomaProject() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void viewSchedule() {
        // TODO implement here
        return ;
    }

    /**\
     * @deprecated
     * @return
     */
    public void viewFinancialInfo() {
        // TODO implement here
        return ;
    }

    /**
     * @param course 
     * @return
     */
    public void registerForCourse(Course course) {
        // TODO implement here
        return ;
    }




}