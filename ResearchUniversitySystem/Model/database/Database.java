package database;

/**
 * @author nurs
 */

import java.io.*;
import java.util.Vector;

import users.UserRepository;
import users.students.StudentOrganization;
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
	
	private int organizationId = 0;
	private int complaintId;

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
    
    
    private Vector<StudentOrganization> organizations;

    private Vector<Complaint> complaints;
    

    private void initRepos() {
    	courseRepo = new CourseRepository();
    	journalRepo = new JournalRepository();
    	newsRepo = new NewsRepository();
    	messageRepo = new MessageRepository();
    	requestRepo = new RequestRepository();
    	registration = new CourseRegistrationService();
    	usersRepo = new UserRepository();
    	researchRepo = new ResearchRepository();
    	organizations= new Vector<>();
        complaints = new Vector<>();
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
    		instance.getUsersRepo().addRootAdmin();
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
    
    public Vector<Complaint> getComplaints() {
		return complaints;
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
     * Produces an id for a new organization instance based on 
     * the current number of organizations in the system
     * 
     * @return	organizationId	a unique identifier of an organization
     */
    public static int generateOrganizationId() {
    	instance.organizationId++;
    	return instance.organizationId;
    }
    
    public static int generateComplaintId() {
    	instance.complaintId++;
    	return instance.complaintId;
    }
    
    

}