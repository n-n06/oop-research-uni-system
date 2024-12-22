package users.employees;

import database.Database;
import enums.Gender;
import enums.School;
import research.*;
import social.messages.Request;

public class ResearcherEmployee extends Employee implements CanBecomeResearcher {
	private School school;
	
	public ResearcherEmployee() {
		becomeResearcher();
	}
	
	public ResearcherEmployee(String firstName, String lastName, int age, Gender gender, School school) {
		super(firstName, lastName, age, gender);
		this.school = school;
	}
	
	public void sendRequest(Request r) {
		Database.instance.getReqeustRepo().addRequest(school, r);
	}
	
	@Override
	public void becomeResearcher() {
		Researcher r = new Researcher(this);;
	}

	@Override
	public boolean getIsResearcher() {
		return true;
	}
}
