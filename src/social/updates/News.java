package social.updates;

import java.util.Vector;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import database.Database;

import enums.NewsType;

/**
 * @author nurs
 */
public class News implements Comparable<News>, Serializable {

    /**
     * Unique identifier
     */
    private int newsId;

    /**
     * Type - can be either research or general news
     */
    private NewsType newsType;

    /**
     * News title
     */
    private String title;
    
    /**
     * News title
     */
    private String content;

    /**
     * Date the news were published
     */
    private Date date;

    /**
     * Comments
     */
    private Vector<Comment> comments;
    

    
    /**
     * Default constructor
     */
    public News() {
//    	newsId = Database.generateNewsId();
    	newsId = Database.instance.getNewsRepo().generateNewsId();
    	date = new Date();
    	comments = new Vector<>();
    }
    
    /**
     * Constructor for searching 
     * */
    public News(int id) {
    	comments = new Vector<>();
    	this.newsId = id;
    }
    
    public News(String title) {
    	this();
    	this.title = title;
    }
    
    public News(String title, String content) {
    	this(title);
    }
    
    public News(String title, NewsType newsT) {
    	this(title);
    	this.newsType = newsT;
    }
    
    public News(String title, NewsType newsT, String content) {
    	this(title, newsT);
    	this.content = content;
    }
    
    
    /**
     * Getters and setters 
     */
    
    public int getNewsId() {
		return newsId;
	}
    
    public NewsType getNewsType() {
		return newsType;
	}
    
    public Date getDate() {
		return date;
	}
    
    public String getTitle() {
		return title;
	}
    
    public String getContent() {
		return content;
	}
    
    public Vector<Comment> getComments() {
		return comments;
	}
    
    public void setTitle(String title) {
		this.title = title;
	}
    
    public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
    
    public void setContent(String content) {
		this.content = content;
	}

    /**
     *Adds comment to news 
     * 
     * @param 	c	comment
     * @return	null
     */
    public void addComment(Comment c) {
    	this.comments.add(c);
    }
    
    /**
     * Prints all of the comment to std output
     * */
    public void viewComments() {
    	for (Comment c : comments) {
    		System.out.println(c + "\n");
    	}
    }
    
    @Override
    public String toString() {
    	return String.format("📰News №%d: %s\nType: %s\n%s\n\n", newsId, title, newsType, content)
    			+ (comments != null ? comments.stream().map(c->c.toString()).collect(Collectors.joining("\n")) : "No comments yet");
    }

    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	News n = (News) obj;
    	return n.newsId == this.newsId;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(newsId, title, date, newsType);
    }
    
    /**
     * Compare by news type then by date
     * */
	@Override
	public int compareTo(News o) {
		if (!this.newsType.equals(o.newsType)) {
			if (this.newsType == NewsType.RESEARCH) {
				return -1;
			} else {
				return 1;
			}
		}
		else {
			return -1 * this.date.compareTo(o.date);
		}
	}

}