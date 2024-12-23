package social.updates;

import java.util.Vector;

import database.Database;
import enums.NewsType;
import enums.School;
import utilities.exceptions.NoResearchersException;

import java.io.Serializable;

/**
 * @author nurs
 */
public class NewsRepository  implements Serializable {
	
	private int newsId;
    /**
     * 
     */
    private Vector<News> news;

    private News autoNews;
    
    /**
     * Default constructor
     */
    public NewsRepository() {
    	news = new Vector<>();
    	autoNews = new News(1);
    	addNews(autoNews);
    	newsId = 0;
    }

    /**
     * Prints all news to std output
     * 
     * @return
     */
    public void displayAllNews() {
    	if (news.size() < 1) {
    		System.out.println("No news yet");
    		return;
    	}
        for (News n : news) {
        	System.out.println(n + "\n");
        }
    }

    /**
     * Adds news to the repository
     * 
     * @param 	n	news instance 
     * @return
     */
    public void addNews(News n) {
        news.add(n);
    }

    /**
     * Removes a news
     * 
     * @param	n	an instance of news created with
     * 			minimum details (id)
     * @return	true 	if removed
     * 			false	if not remove
     */
    public boolean removeNews(News n) {
        return news.remove(n);
    }

    /**
     * Returns news
     * 
     * @param 	n	an instance of news created with
     * 			minimum details (id)
     * @return	n	a concrete news instance
     */
    public News getNews(News n) {
        return news.get(news.indexOf(n));
    }
    
    /**
     * Produces an id for a new news instance based on 
     * the current number of news in the system
     * 
     * @return	newsId	a unique identifier of news
     */
    public int generateNewsId() {
    	newsId++;
    	return newsId;
    }
    
    public void generateNews() throws NoResearchersException {
    	String news = "Top Researcher of All University: " 
    			+ Database.instance.getResearchRepo().getTopCitedResearcher().getFullName() + "\n";
    	for (School s : School.values()) {
    		news += "Top Researcher of " + s + ": " + Database.instance.getResearchRepo().getTopCitedResearcher(s).getFullName() + "\n";
    	}
    	autoNews.setContent(news);
    }


}