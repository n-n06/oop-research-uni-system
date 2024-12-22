package social.messages;

import enums.UrgencyLevel;
import enums.School;
import users.User;
import users.students.Student;
import users.employees.*;

import java.io.*;
import java.util.*;

import database.Database;

/**
 * 
 */
public class Complaint implements Serializable, Comparable<Complaint> {
	
	private int complaintId;
	private Teacher sender;
	private Dean receiver;
	private Date date;
	private String content;
	private Student reason;
    private UrgencyLevel urgencyLevel;
    
    /*
     * Main constructor uwu
     * */
    {
    	
    	date = new Date();
    }
    
    public Complaint() {
    	complaintId = Database.generateComplaintId();
    }
    
    public Complaint(int id) {
    	this.complaintId = id;
    }
    
    public Complaint(Teacher sender, String content, UrgencyLevel level) {
    	this();
    	this.sender = sender;
    	this.content = content;
    	this.urgencyLevel = level;
    }
    
    public Complaint(Teacher sender, Dean receiver, String content, UrgencyLevel level) {
    	this();
    	this.sender = sender;
    	this.receiver = receiver;
    	this.content = content;
    	this.urgencyLevel = level;
    }
    
    public Complaint(Teacher sender, Dean receiver, String content, UrgencyLevel level, Student reason) {
    	this(sender, receiver, content, level);
    	this.reason = reason;
    }
    
    public School getSchool() {
    	return reason.getSchool();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) return false;
    	if (obj == this) return true;
    	if (obj.getClass() != this.getClass()) return true;
    	
    	Complaint c = (Complaint) obj;
    	return c.complaintId == this.complaintId;
    }

    @Override
    public String toString() {
    	return String.format("💢📢Complaint #%d:\n", complaintId)
    			+ "Urgency level: " + urgencyLevel.toString().toLowerCase() + "\n"
				+ "Sent: " + date
    			+ "Sender: " + sender.getFullName() + " " + sender.getEmail()
    			+ "Student in question: " + reason.getFullName() + " " + reason.getEmail() + "\n"
    			+ content + "\n";
    			
    }

	@Override
	public int compareTo(Complaint c) {
		return -1 * this.urgencyLevel.compareTo(c.urgencyLevel);
	}




}