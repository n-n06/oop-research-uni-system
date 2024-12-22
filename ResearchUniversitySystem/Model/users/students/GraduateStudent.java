package users.students;

import enums.*;
import users.employees.Teacher;
import research.Researcher;

/**
 * 
 */
public class GraduateStudent extends Student {
	private int year;
    /**
     * Default constructor
     */
    public GraduateStudent() {
    	becomeResearcher();
    }
    
    public GraduateStudent(String firstName, String lastName, int age, Gender gender, Degree degree, School school) {
    	super(firstName, lastName, age, gender, degree, school, 1);
    }

    @Override
    public void setYear(int year) {
    	if (year > 0 && year < 3) {
    		this.year = year;
    	};
    }


}