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

 
    public BachelorStudent(String firstName, String lastName, String email, int age, Gender gender, School school) {
    	super(firstName, lastName, email, age, gender, Degree.BACHELOR, school);
    }
    


  
   
}