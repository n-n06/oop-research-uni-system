package courses;

import java.util.HashMap;
import java.util.Map.Entry;
import java.io.Serializable;

import courses.RegistrationRequest;


/**
 * @author Muslik
 */
public class CourseRegistrationService implements Serializable {

	private HashMap<Integer, RegistrationRequest> registryRequests;
    private boolean isOpen;
    
    
    public CourseRegistrationService() {
    	this.registryRequests = new HashMap<>();
    	this.isOpen = false;
    }

    public void addRegRequest(RegistrationRequest regReq) {
        registryRequests.put(regReq.getRegRequestid(), regReq);
    }

    public RegistrationRequest getRegRequest(Integer id) {
        return registryRequests.get(id); 
    }

    public boolean removeRegRequest(Integer id) {
        return registryRequests.remove(id) != null; 
    }

    public boolean hasRegRequest(Integer id) {
        return registryRequests.containsKey(id);
    }

    public void clearAllRequests() {
        registryRequests.clear();
    }
    
    public void openRegistration() {
        if (isOpen) {
            System.out.println("Registration is already open.");
        } else {
            this.isOpen = true;
            System.out.println("Registration opened.");
        }
    }

    public void closeRegistration() {
        if (!isOpen) {
            System.out.println("Registration is already closed.");
        } else {
            this.isOpen = false;
            System.out.println("Registration closed.");
        }
    }
    
    public void displayRegRequests() {
    	System.out.println("All registration requests:");
    	for (Entry<Integer, RegistrationRequest> rq : registryRequests.entrySet()) {
    		System.out.println(rq.getValue());
    	}
    }

}