package users.employees;

import database.Database;
import enums.Gender;
import enums.School;
import enums.UserType;
import research.*;
import social.messages.Request;
import utilities.logging.LoggerProvider;

public class ResearcherEmployee extends Employee implements CanBecomeResearcher {
	private School school;
	private Researcher resAccount;
	
	public ResearcherEmployee() {
		becomeResearcher();
	}
	
	public ResearcherEmployee(String firstName, String lastName, int age, Gender gender, School school) {
		super(firstName, lastName, age, gender);
		this.school = school;
	}
	
    @Override
    public UserType getUserType() {
    	return UserType.RESEARCHER;
    }
	
	@Override
	public School getSchool() {
		return school;
	}
	
    @Override
    public void setSchool(School s) {
    	this.school = s;
    }
	
	public void sendRequest(Request r) {
		LoggerProvider.getLogger().info(getEmail() + " has sent a request ");
		Database.instance.getReqeustRepo().addRequest(school, r);
	}
	
	@Override
	public void becomeResearcher() {
		Researcher resAccount = new Researcher(this);;
	}

	@Override
	public boolean getIsResearcher() {
		return true;
	}
	
	@Override
	public String toString() {
		return resAccount.toString();
	}
}
