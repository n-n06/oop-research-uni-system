package users.employees;

import users.BaseUser;

import java.util.TreeSet;

import enums.Gender;
import research.CanBecomeResearcher;
import utilities.social.Message;
import utilities.social.MessageRepository;
import utilities.social.Request;
import utilities.social.WorkMessage;

/**
 * 
 */
public class Employee extends BaseUser {
	private MessageRepository messageRepo;

    public Employee() {
    }
    
    
    public Employee(String firstName, String lastName, String email, int age, Gender gender) {
    	super(firstName, lastName, email, age, gender);
    }

  
    public void sendWorkMessage(Employee employee, Message message) {
        try {
        	message.setReceiver(employee);
        	messageRepo.addMessage(employee, message);
        	System.out.println("Message sent to "+ employee.getFirstName() + " " + employee.getLastName());
        } catch (Exception e) {
        	System.out.println("Fail!");
        }
        return ;
    }
    public void readWorkMessage() {
    	try {
    		System.out.println("Work Messages for "+ this.getFirstName() + " " + this.getLastName());
    		TreeSet<Message> messages = messageRepo.getMessages(this);
    		if (messages == null || messages.isEmpty()) {
    			System.out.println("No messages available");
    			return;
            }
    		for (Message message : messages) {
    			if (message instanceof WorkMessage) {
    				System.out.println(message);
    			}
    		}
    	} catch (Exception e) {
             System.out.println("Failed to read " + e.getMessage());
         }
    }
    public void sendRequest(Request request) {
        // TODO implement here
    }

	@Override
	public void becomeResearcher() {
		// TODO Auto-generated method stub
		
	}

}