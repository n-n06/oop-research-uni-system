package database;

import java.io.*

import users.UsersRepository;
import utilities.social.RequestRepository;
import courses.CourseRepository;
import courses.CourseRegistrationService;
import menuInfo.NewsRepository;
import menuInfo.JournalsRepository;

import UITextStorage.uP;
/**
 * 
 */
public class Database implements Serializable {
    /**
     * 
     */
    public static Database instance;

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
    
    static {
    	if (new File("data").exists()) {
    		try {
    			
    		} catch (Exception e) {
    			System.err.println(e.getMessage());
    		}
    	} else {
    		instance = new Database()
    	}
    }
    
    /**
     * 
     */
    private Database() {
        // TODO implement here
    }

    /**
     * @return
     */
    public static Database read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Database) ois.readObject();
    }

    /**
     * @return
     */
    public static void write() throws IOException {
    	FileOutputStream fos = new FileOutputStream("data");
    	ObjectOutputStream ous = new ObjectOutputStream(fos);
    	ous.writeObject(instance);
    	ous.close();
    }
    
    public static int getUserId() {
    	return instance.userRepo.users.size() + 1;
    }

}