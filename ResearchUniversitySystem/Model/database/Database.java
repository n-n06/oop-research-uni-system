package database;

/**
 * @author nurs
 */

import java.io.*;
import java.util.Vector;

import users.UserRepository;

import courses.CourseRepository;
import courses.CourseRegistrationService;

import research.ResearchRepository;

import social.messages.Complaint;
import social.messages.MessageRepository;
import social.messages.RequestRepository;
import social.updates.JournalRepository;
import social.updates.NewsRepository;

/**
 * Singleton DB
 */
public class Database implements Serializable {
	private int userId = 0;
	private int journalId = 0 ;
	private int newsId = 0;
	private int messageId = 0;
	private int regRequestId = 0;
	private int organizationId = 0;
	private int researchProjectId = 0;
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

    
    private ResearchRepository researchRepo;
    
    private Vector<Complaint> complaints;
    

    

    private void initRepos() {
    	courseRepo = new CourseRepository();
    	journalRepo = new JournalRepository();
    	newsRepo = new NewsRepository();
    	messageRepo = new MessageRepository();
    	requestRepo = new RequestRepository();
    	registration = new CourseRegistrationService();
    	usersRepo = new UserRepository();
    	
    	complaints = new Vector<>();
    	researchRepo = new ResearchRepository();
    }
    
    /**
     * Private constr to ensure Singleton
     */
    
    private Database() {
		initRepos();
    }
    
    /**
     *Static init block to create the instance on load 
     */
    static {
    	if (new File("data").exists()) {
    		System.out.println("Savepoint!");
    		try {
    			instance = read();
    		} catch (Exception e) {
    			System.err.println(e.getMessage());;
    		}
    	} else {
    		instance = new Database();
    		instance.usersRepo.addRootAdmin();
    	}
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
    
    public ResearchRepository getResearchRepo() {
		return researchRepo;
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
    	instance.userId++;
    	return instance.userId;
    }
    
    /**
     * Produces an id for a new journal based on 
     * the current number of journals in the system
     * 
     * @return	journalId	a unique identifier of a journal
     */
    public static int generateJournalId() {
    	instance.journalId++;
    	return instance.journalId;
    }
    
    /**
     * Produces an id for a new news instance based on 
     * the current number of news in the system
     * 
     * @return	newsId	a unique identifier of news
     */
    public static int generateNewsId() {
    	instance.newsId++;
    	return instance.newsId;
    }
    
    /**
     * Produces an id for a new message instance based on 
     * the current number of mesage in the system
     * 
     * @return	messageId	a unique identifier of a message
     */
    public static int generateMessageId() {
    	instance.messageId++;
    	return instance.messageId;
    }
    
    /**
     * Produces an id for a new registration request instance based on 
     * the current number of registration requests in the system
     * 
     * @return	regRequestId	a unique identifier of a registration request
     */
    public static int generateRegRequestId() {
    	instance.regRequestId++;
    	return instance.regRequestId;
    }

    /**
     * Produces an id for a new organization instance based on 
     * the current number of organizations in the system
     * 
     * @return	organizationId	a unique identifier of an organization
     */
    public static int generateOrganizationId() {
    	instance.organizationId++;
    	return instance.organizationId;
    }
    
    /**
     * Produces an id for a new research project instance based on 
     * the current number of research projects in the system
     * 
     * @return	researchProjectId	a unique identifier of a research project
     */
    public static int generateResearchProjectId() {
    	instance.researchProjectId++;
    	return instance.researchProjectId;
    }
    
    

}