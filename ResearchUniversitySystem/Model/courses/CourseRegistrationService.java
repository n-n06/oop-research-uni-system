package courses;

import java.util.HashMap;
import java.util.Map.Entry;
import java.io.Serializable;

import courses.RegistrationRequest;


/**
 * @author Muslik
 */
public class CourseRegistrationService implements Serializable {
	private int regRequestId;
	private HashMap<Integer, RegistrationRequest> registryRequests;
    private boolean isOpen;
    
    
    public CourseRegistrationService() {
    	this.registryRequests = new HashMap<>();
    	this.isOpen = false;
    	regRequestId = 0;
    }

    public void addRegRequest(RegistrationRequest regReq) {
    	if (isOpen) {
            registryRequests.put(regReq.getRegRequestid(), regReq);
    	}
    }
    
    public boolean removeRegRequest(Integer id) {
    	if (isOpen) {
            return registryRequests.remove(id) != null; 
    	}
    	return false;
    }
    
    public void clearAllRequests() {
    	if (isOpen) {
            registryRequests.clear();
    	}
    }

    public RegistrationRequest getRegRequest(Integer id) {
    	if (isOpen) {
            return registryRequests.get(id); 
    	}
		return null; 
    }

    public boolean hasRegRequest(Integer id) {
    	if (isOpen) {
            return registryRequests.containsKey(id);
    	}
    	return false;
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
    
    public boolean checkIsOpen() {
    	return isOpen;
    }
    
    
    
    /**
     * Produces an id for a new registration request instance based on 
     * the current number of registration requests in the system
     * 
     * @return	regRequestId	a unique identifier of a registration request
     */
    public int generateRegRequestId() {
    	regRequestId++;
    	return regRequestId;
    }

}