package social.messages;

import java.util.Date;
import java.io.Serializable;

import database.Database;
import enums.RequestStatus;
import users.User;
import users.employees.*;

/**
 * 
 */
public class Request extends Message implements Serializable {

    private int requestId;

    private User sender;
    
    private boolean signed;

    private RequestStatus status;
    
    
    /**
     * Default constructor
     */
    public Request() {
    	requestId = Database.instance.getReqeustRepo().generateRequestId();
    }
    
    public Request(User sender, Employee receiver, String content) {
    	super(receiver, content);
    	this.sender = sender;
    }

    public User getSender() {
		return sender;
	}
    
    private boolean getSigned() {
		return signed;
	}
    
    public void sign(Dean d) {
		this.signed = true;
	}
    
    public RequestStatus getStatus() {
		return status;
	}
    
    public void setStatus(RequestStatus status) {
		this.status = status;
	}
    
    
    @Override
    public String toString() {
    	return String.format("Request #%d: \n", requestId) 
    			+ "\nSent: " + getDate() + "\n"
    			+ "Sender: " + sender.getFullName() + " " + sender.getEmail() + "\n"
    			+ getContent() + "\n"
    			+ "Status: " + (signed ? "Signed" : "Not signed") + " " 
    			+ status.toString().toLowerCase().replace('_', ' ');
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	Request r = (Request) obj;
    	return r.requestId == this.requestId;
    }



}