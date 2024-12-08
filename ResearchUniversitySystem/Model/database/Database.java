package database;

import java.io.Serializable;

import users.UsersRepository;
import utilities.social.RequestRepository;
import courses.CourseRepository;
import courses.CourseRegistrationService;
import menuInfo.NewsRepository;
import menuInfo.JournalsRepository;

/**
 * 
 */
public class Database implements Serializable {

    /**
     * Default constructor
     */

    /**
     * 
     */
    private static Database instance;

    /**
     * 
     */
    private UsersRepository usersRepo;

    /**
     * 
     */
    private UITextStorage UIText;

    /**
     * 
     */
    private JournalsRepository journalRepo;

    /**
     * 
     */
    private NewsRepository newsRepo;

    /**
     * 
     */
    private CourseRepository courseRepo;

    /**
     * 
     */
    private RequestRepository reqeustRepo;

    /**
     * 
     */
    private CourseRegistrationService registration;

    /**
     * 
     */
    private Database() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static Database read() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public static void write() {
        // TODO implement here
        return;
    }

}