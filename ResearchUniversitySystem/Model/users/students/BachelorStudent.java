package users.students;

import enums.*;
import users.UserRepository;
/**
 * 
 */
public class BachelorStudent extends Student {
	 public int year;
	 
	 public BachelorStudent() {
		 
	 }

 
    public BachelorStudent(String firstName, String lastName, int age, Gender gender, School school) {
    	super(firstName, lastName, age, gender, Degree.BACHELOR, school, 1);
    }


	@Override
	boolean checkLastYear() {
		return this.year == 4;
	}


    


  
   
}