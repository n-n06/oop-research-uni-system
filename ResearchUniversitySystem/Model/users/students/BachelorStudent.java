package users.students;

/**
 * 
 */
public class BachelorStudent extends Student {
	 public int year;

 
    public BachelorStudent(String firstName, String lastName, String email, int year) {
    	super(firstName, lastName, email);
    	this.year = year;
    }
    
    @Override
    public boolean login(String email, String password, UserRepository userRepo) {
    	super.login(getUserEmail(), getPassword(), userRepo);
    }

  
   
}