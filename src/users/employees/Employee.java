package users.employees;

import users.BaseUser;
import utilities.logging.LoggerProvider;

import java.util.TreeSet;

import database.Database;
import enums.Gender;
import research.CanBecomeResearcher;
import social.messages.Message;
import social.messages.MessageRepository;
import social.messages.Request;
import social.messages.WorkMessage;

/**
 * @author yasmin
 */
public abstract class Employee extends BaseUser {

    public Employee() {
    }
    
    
    public Employee(String firstName, String lastName, int age, Gender gender) {
    	super(firstName, lastName, age, gender);
    }

  
    public void sendWorkMessage(Employee employee, Message message) {
    	LoggerProvider.getLogger().info(this.getEmail() + " sent message to " + employee.getEmail());
        try {
        	message.setReceiver(employee);
        	Database.instance.getMessageRepo().addMessage(employee, message);
        	System.out.println("Message sent to "+ employee.getFullName());
        } catch (Exception e) {
        	System.out.println("Fail!");
        }
        return ;
    }
    
    public void readWorkMessage() {
    	try {
    		System.out.println("📬Work Messages for "+ this.getFullName());
    		TreeSet<Message> messages = Database.instance.getMessageRepo().getMessages(this);
    		if (messages == null || messages.isEmpty()) {
    			System.out.println("📭No messages available");
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

    

}