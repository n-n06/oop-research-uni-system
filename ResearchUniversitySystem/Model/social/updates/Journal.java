package social.updates;

import java.io.Serializable;
import java.util.*;

import database.Database;
import research.ResearchPaper;
import social.messages.JournalNotification;
//import research.Researcher;
import users.User;
import enums.Language;

/**
 * 
 */
public class Journal implements Serializable {

    /**
     * Unique identifier
     */
    public int journalId;

    /**
     * Name
     */
    private String name;

    /**
     * EN, KZ or RU
     */
    private Language language;

    /**
     * Scientific field that is covered by
     * the articles in this journal
     */
    private String topic;

    /**
     * Short summary of the journal
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
    
    {
    	journalId = Database.generateJournalId();
    	this.articles = new Vector<>();
    }
    
    
    public Journal() {
    }
    
    public Journal(String name) {
    	this.name = name;
    }
    
    public Journal(String name, Language l) {
    	this.name = name;
    	this.language = l;
    }
    
    public Journal(String name, String topic) {
    	this(name);
    	this.topic = topic;
    }
    
    public Journal(String name, String topic, Language l) {
    	this(name, topic);
    	this.language = l;
    }
    
    public Journal(String name, String topic, String description) {
    	this(name, topic);
    	this.description = description;
    }
    
    public Journal(String name, String topic, String description, Language l) {
    	this(name, topic, description);
    	this.language = l;
    }
    
    public String getName() {
		return name;
	}
    
    public int getId() {
		return journalId;
	}
    
    public Language getLanguage() {
		return language;
	}
    
    public void setLanguage(Language language) {
		this.language = language;
	}
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
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
    
    public void setTopic(String topic) {
		this.topic = topic;
	}

    /**
     * Displays all of the articles in the terminal
     * 
     * @return
     */
    public void displayArticles() {
    	for (ResearchPaper art : articles) {
    		System.out.println(art); //TODO: add stuff later ig)
    	}
    }

    /**
     * Publishes an article
     * 
     * @param	article 	an article to publish
     * @return	null		nothing
     */
    public void publishArticle(ResearchPaper article) {
        articles.add(article);
        notifyAllSubscribers(article);
    }

    /**
     * Removes an article from a journal
     * 
     * @param 	article 	an article to remove 
     * @return	true 		if removed
     * 			false 		if not found and not removed
     */
    public boolean removeArticle(ResearchPaper article) {
        return articles.remove(article);
    }

    /**
     * Adds subscriber (Observer pattern)
     * 
     * @param	user	new subscriber 
     * @return	null	nothing
     */
    public void addSubscriber(User user) {
        subscribers.add(user);
    }

    /**
     * Removes a subscriber from a subs list
     * 
     * @param 	user 	subscriber to remove
     * @return	true	if removed
     * 			false	if not found and not removed
     */
    public void removeSubscriber(User user) {
    	subscribers.remove(user);
    }

    /**
     * Sends jounral notifications to all of the
     * subscribers
     * 
     * @param 	article		article that trigerred 
     * 						the notification
     * @return	null		nothing
     */
    public void notifyAllSubscribers(ResearchPaper article) {
    	for (User subscriber: subscribers) {
    		Database.instance
    			.getMessageRepo()
    			.addMessage(subscriber, new JournalNotification(subscriber, this, article));
    	}	
    }
    
    @Override
    public boolean equals(Object obj) {
    	/**
    	 * Check equality based on the unique ID
    	 * 
    	 * @param 	obj		another journal to check for equality
    	 * 
    	 * @return 	true	if the same journal
    	 * 			false 	if not the same
    	 * */
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	Journal j = (Journal) obj;
    	return j.journalId == this.journalId; 
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(journalId, name, topic, description, subscribers, articles);
    }
    
    @Override
    public String toString() {
    	return String.format("Journal №%d: \'%s\'", journalId, name) + "\n" + 
    			"Topic: " + topic + "\n" + "Description" + description + "\n" + 
    			"Number of articles: " + articles.size();
    }
    
    

}