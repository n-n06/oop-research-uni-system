package database;

/**
 * @author nurs
 */

import java.io.*;

import users.UserRepository;
import utilities.social.MessageRepository;
import utilities.social.RequestRepository;
import courses.CourseRepository;
import courses.CourseRegistrationService;
import menuInfo.NewsRepository;
import menuInfo.JournalRepository;


/**
 * Singleton DB
 */
public class Database implements Serializable {
	private static int userId = 0;
	private static int journalId = 0;
	private static int newsId = 0;
	private static int messageId = 0;
    /**
     * 
     */
    public static Database instance;

    /**
     * Repository to interact (CRUD operations) with the storage of All Users
     */
    private UserRepository usersRepo;

    /**
     * 
     */
    private UITextStorage UIText;

    /**
     * Repository to interact with the storage of All Journals
     */
    private JournalRepository journalRepo;

    /**
     * Repository to interact with the storage of All News
     */
    private NewsRepository newsRepo;

    /**
     * Repository to interact with the storage of All Courses
     */
    private CourseRepository courseRepo;

    /**
     * Repository to interact with the storage of All Requests(excluding registration requests)
     */
    private RequestRepository requestRepo;

    /**
     * Repository to interact with the storage of All Registration Requests
     */
    private CourseRegistrationService registration;
    
    /**
     * Repository to interact with the storage of All Messages
     */
    private MessageRepository messageRepo;
    
    /**
     *Static init block to create the instance on load 
     */
    static {
    	if (new File("data").exists() && instance != null) {
    		try {
    			read();
    		} catch (Exception e) {
    			System.err.println(e.getMessage());
    		}
    	} else {
    		instance = new Database();
    	}
    }
  
    private void initRepos() {
    	usersRepo = new UserRepository();
    	courseRepo = new CourseRepository();
    	journalRepo = new JournalRepository();
    	newsRepo = new NewsRepository();
    	messageRepo = new MessageRepository();
    	requestRepo = new RequestRepository();
    	registration = new CourseRegistrationService();
    }
    
    /**
     * Private constr to ensure Singleton
     */
    private Database() {
		initRepos();
    }
    
    
    /**
     * 
     * Getters
     * */
    
    public JournalRepository getJournalRepo() {
		return journalRepo;
	}
    
    public MessageRepository getMessageRepo() {
		return messageRepo;
	}
    
    public CourseRepository getCourseRepo() {
		return courseRepo;
	}
    
    public NewsRepository getNewsRepo() {
		return newsRepo;
	}
    
    public CourseRegistrationService getRegistration() {
		return registration;
	}
    
    public RequestRepository getReqeustRepo() {
		return requestRepo;
	}
    
    public UITextStorage getUIText() {
		return UIText;
	}
    
    public UserRepository getUsersRepo() {
		return usersRepo;
	}
    
    

    /**
     * Deserializes a database from a 'data' file
     * 
     * @return the instance of singleton Database
     */
    public static Database read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Database) ois.readObject();
    }

    /**
     * Serializes a database into a 'data' file
     * 
     * @return null
     */
    public static void write() throws IOException {
    	FileOutputStream fos = new FileOutputStream("data");
    	ObjectOutputStream ous = new ObjectOutputStream(fos);
    	ous.writeObject(instance);
    	ous.close();
    }
    
    /**
     * Produces an id for a new user based on 
     * the current number of users in the system
     * 
     * @return	userId	a unique identifier of a user
     */
    public static int generateUserId() {
    	userId++;
    	return userId;
    }
    
    /**
     * Produces an id for a new journal based on 
     * the current number of journals in the system
     * 
     * @return	userId	a unique identifier of a journal
     */
    public static int generateJournalId() {
    	journalId++;
    	return journalId;
    }
    
    /**
     * Produces an id for a new news instance based on 
     * the current number of news in the system
     * 
     * @return	userId	a unique identifier of news
     */
    public static int generateNewsId() {
    	newsId++;
    	return newsId;
    }
    
    /**
     * Produces an id for a new message instance based on 
     * the current number of mesage in the system
     * 
     * @return	userId	a unique identifier of a message
     */
    public static int generateMessageId() {
    	messageId++;
    	return messageId;
    }


}