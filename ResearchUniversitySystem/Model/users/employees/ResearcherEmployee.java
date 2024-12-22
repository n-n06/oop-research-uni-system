package users.employees;

import enums.Gender;
import enums.School;
import research.*;

public class ResearcherEmployee extends Employee implements CanBecomeResearcher {
	private School school;
	
	public ResearcherEmployee() {
		becomeResearcher();
	}
	
	public ResearcherEmployee(String firstName, String lastName, int age, Gender gender, School school) {
		super(firstName, lastName, age, gender);
		this.school = school;
	}
	
	public void sendRequest() {
		
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
