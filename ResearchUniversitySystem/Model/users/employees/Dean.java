package users.employees;


import database.Database;
import enums.Gender;
import enums.School;
import enums.UserType;
import social.messages.Request;
import utilities.logging.LoggerProvider;

public class Dean extends Employee {

	private School school;
	
    public Dean() {
    	
    }
    
    public Dean(String firstName, String lastName, int age, Gender gender, UserType ut, School school) {
    	super(firstName, lastName, age, gender);
    	this.school = school;
    }
    
    public void setSchool(School school) {
		this.school = school;
	}

    @Override
    public UserType getUserType() {
    	return UserType.DEAN;
    }
    
    public void signRequest(Request request) {
    	LoggerProvider.getLogger().info(getEmail() + " has signed request sent by " + request.getReceiver().getEmail() + " on " + request.getDate());
        Database.instance.getReqeustRepo().acceptRequest(school, request);
    }

    public void rejectRequest(Request request) {
    	LoggerProvider.getLogger().info(getEmail() + " has rejected request sent by " + request.getReceiver().getEmail() + " on " + request.getDate());
        Database.instance.getReqeustRepo().declineRequest(school, request);
    }

    public void viewRequests() {
    	Database.instance.getReqeustRepo().viewRequests(school);
    }
    public void viewComplaints() {
    	System.out.println("Complaints from teachers: \n");
        Database.instance.getComplaints().stream()
        	.filter(c->c.getSchool() == this.school)
        	.sorted()
        	.forEach(c->System.out.println(c));
    }
    
    @Override
    public String toString() {
    	return "🧑‍💼Dean of " + school.toString()
    			+ "\n" + super.toString();
    }

}