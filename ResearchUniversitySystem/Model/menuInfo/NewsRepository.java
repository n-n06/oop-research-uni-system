package menuInfo;

import java.util.Vector;

/**
 * @author nurs
 */
public class NewsRepository {

    /**
     * 
     */
    private Vector<News> news;

    /**
     * Default constructor
     */
    public NewsRepository() {
    }

    /**
     * Prints all news to std output
     * 
     * @return
     */
    public void displayAllNews() {
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
    


}