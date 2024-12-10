package users.employees;

import users.BaseUser;
import enums.Gender;
import research.CanBecomeResearcher;
import utilities.social.Message;
import utilities.social.MessageRepository;
import utilities.social.Request;

/**
 * 
 */
public class Employee extends BaseUser {
	private MessageRepository messageRepo;
    /**
     * Default constructor
     */
    public Employee() {
    }
    
    public Employee(String firstName, String lastName, String email, int age, Gender gender) {
    	super(firstName, lastName, email, age, gender);
    }

  
    public void sendWorkMessage(Employee employee, Message message) {
        try {
        	message.setReceiver(employee);
        	messageRepo.addMessage(employee, message);
        	System.out.println("Message sent to"+ employee.getName());
        } catch (Exception e) {
        	System.out.println("Fail!");
        }
        return ;
    }

    /**
     * @return
     */
    public void readWorkMessage() {
        // TODO implement here
        return ;
    }

    /**
     * @param request
     */
    public void sendRequest(Request request) {
        // TODO implement here
    }

}