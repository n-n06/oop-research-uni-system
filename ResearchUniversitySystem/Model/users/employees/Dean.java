package users.employees;


import database.Database;
import enums.Gender;
import enums.School;
import social.messages.Request;

public class Dean extends Employee {

	School school;
	
    public Dean() {
    	
    }
    
    public Dean(String firstName, String lastName, int age, Gender gender, School school) {
    	super(firstName, lastName, age, gender);
    	this.school = school;
    }

    public void signRequest(Request request) {
        Database.instance.getReqeustRepo().acceptRequest(school, request);
    }

    public void rejectRequest(Request request) {
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