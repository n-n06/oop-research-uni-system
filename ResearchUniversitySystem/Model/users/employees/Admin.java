package users.employees;

import enums.Gender;
import users.User;

/**
 * @author ExpertBook
 */
public class Admin extends Employee {

    /**
     * Default constructor
     */
    public Admin() {
    }
    
    public Admin(String email) {
    	setEmail(email);
    }
    
    public Admin(String firstName, String lastName, String email, int age, Gender gender) {
    	super(firstName, lastName, email, age, gender);
    }

    /**
     * @param user 
     * @return
     */
    public void addUser(User user) {
        // TODO implement here
        return ;
    }

    /**
     * @param user 
     * @return
     */
    public void updateUser(User user) {
        // TODO implement here
        return ;
    }

    /**
     * @param user 
     * @return
     */
    public void deleteUser(User user) {
        // TODO implement here
        return ;
    }

    /**
     * @param user 
     * @return
     */
    public void viewUserInfo(User user) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void readLogs() {
        // TODO implement here
        return ;
    }

}
