package social.messages;

import java.util.Date;
import java.io.Serializable;

/**
 * 
 */
public class Request implements Serializable {

    /**
     * Default constructor
     */
    public Request() {
    }

    /**
     * 
     */
    private int Id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private boolean signed;

    /**
     * 
     */
    private boolean accepted;

    /**
     * 
     */
    private Date date;

}