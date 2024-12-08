package menuInfo;

import java.util.Vector;
import java.util.Date;	

import enums.NewsType;
import menuInfo.Comment;

/**
 * 
 */
public class News implements Comparable<News> {

    /**
     * Default constructor
     */
    public News() {
    }

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private NewsType newsType;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */

    /**
     * 
     */
    private Vector<Comment> comments;

    /**
     * 
     */
    public void displayNews() {
        // TODO implement here
    }

    /**
     * 
     */
    public void filterByType() {
        // TODO implement here
    }

	@Override
	public int compareTo(News o) {
		// TODO Auto-generated method stub
		return 0;
	}

}