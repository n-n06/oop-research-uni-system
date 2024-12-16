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

 
    public BachelorStudent(String firstName, String lastName, String email, int age, Gender gender, int year) {
    	super(firstName, lastName, email, age, gender);
    	this.year = year;
    }
    
    @Override
    public boolean login(String email, String password, UserRepository userRepo) {
    	return super.login(getEmail(), getPassword(), userRepo);
    }

  
   
}