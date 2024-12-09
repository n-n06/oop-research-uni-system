package utilities.social;

/**
 * @author nurs
 * */

import java.util.Date;

import menuInfo.Journal;
import research.ResearchPaper;
import users.User;

/**
 * Journal notifications that come after an article has 
 * been posted in a subscribed journal
 * */
public class JournalNotification extends Message {
	/**
	 * Journal the notification is from
	 * */
	private Journal journal;
	
	/**
	 * The article that trigerred the notification
	 * */
	private ResearchPaper article;
	
	/**
	 * Default constructor
	 * */
    public JournalNotification() {
    }
    
	/**
	 * Main constructor
	 * */
    public JournalNotification(User receiver, Journal journal, ResearchPaper article) {
    	super(receiver);
    	this.journal = journal;
    	this.article = article;
    }
    
    @Override
    public String toString() {
    	return String.format("📝New article in \'%s\': %s", this.journal.getName(), this.article);
    }
    
    @Override
    public int compareTo(Message m) {
    	return super.compareTo(m);
    }
}
