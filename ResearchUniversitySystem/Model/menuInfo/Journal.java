package menuInfo;

import java.io.*;
import java.util.*;

//import Database
import research.ResearchPaper;
import research.Researcher;
import users.User;
import enums.Language;

/**
 * 
 */
public class Journal {

    /**
     * Default constructor
     */
    public Journal() {
    }

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Language language;

    /**
     * 
     */
    private String topic;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Vector<ResearchPaper> articles;

    /**
     * 
     */
    private Vector<User> subscribers;
    
    public String getName() {
		return name;
	}
    
    public int getId() {
		return id;
	}
    
    public Language getLanguage() {
		return language;
	}
    
    public String getDescription() {
		return description;
	}
   
    public Vector<ResearchPaper> getArticles() {
		return articles;
	}
    
    public Vector<User> getSubscribers() {
		return subscribers;
	}
    
    public String getTopic() {
		return topic;
	}

    /**
     * @return
     */
    public void displayArticles() {
        // TODO implement here
        return;
    }

    /**
     * @param article 
     * @return
     */
    public void publishArticle(ResearchPaper article) {
        
        return;
    }

    /**
     * @param article 
     * @return
     */
    public boolean removeArticle(ResearchPaper article) {
        // TODO implement here
        return false;
    }

    /**
     * @param user 
     * @return
     */
    public void addSubscriber(User user) {
        subscribers.add(user);
    }

    /**
     * @param user 
     * @return
     */
    public void removeSubscriber(User user) {
    	subscribers.remove(user);
    }

    /**
     * 
     * @return
     */
    public void notifyAllSubscribers() {
    	for (User subscriber: subscribers) {
    		
    	}	
    }

}