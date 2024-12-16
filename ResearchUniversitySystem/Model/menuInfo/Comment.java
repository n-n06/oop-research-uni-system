package menuInfo;

import java.util.Date;

import users.User;

/**
 * 
 */
public class Comment implements Comparable<Comment> {

    /**
     * 
     */
    private User author;

    /**
     * 
     */
    private String text;
    
    /**
     * 
     */
    private Date date;
    
    {
    	date = new Date();
    }
    
    /**
     * Default constructor
     */
    public Comment() {
    }
    
    /**
     * 
     */
    public Comment(User author, String text) {
    	this.author = author;
    	this.text = text;
    }
    
    public void setText(String text) {
		this.text = text;
	}
    
    @Override
    public String toString() {
    	return "💭 " + date + " " +  author + ":\n" + text;
    }

	@Override
	public int compareTo(Comment o) {
		return -1 * date.compareTo(o.date);
	}
    
    

}