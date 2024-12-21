package social.messages;


import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;
import java.io.Serializable;

import enums.RequestStatus;
import enums.School;
import users.employees.Dean;
/**
 * 
 */
public class RequestRepository implements Serializable  {
	
	private int requestId = 0;
    /**
     * Default constructor
     */
    public RequestRepository() {
    }

    /**
     * 
     */
    private HashMap<School, Vector<Request>> requests;

    /**
     * @param req 
     * @return
     */
    public void addRequest(School school, Request req) {
    	requests.get(school).add(req);
        return ;
    }

    /**
     * @param request 
     * @return
     */
    public boolean removeRequest(School school, Request request) {
        return requests.get(school).remove(request);
    }

    /**
     * @param request 
     * @return
     */
    public Request getRequest(School school, Request request) {
    	Vector<Request> r = requests.get(school);
        return r.elementAt(r.indexOf(request));
    }
    
    /**
     * @return
     */
    public void viewRequests(School school) {
    	System.out.println("🗳Requests for the Dean's office of " + school);
        requests.get(school).stream().sorted().forEach(r->System.out.println(r));
    }
    
    public int generateRequestId() {
    	requestId++;
    	return requestId;
    }

    /**
     * @return
     */
    public void acceptRequest(School school, Request r) {
    	r = getRequest(school, r);
    	r.setStatus(RequestStatus.ACCEPTED);
    }

    /**
     * @return
     */
    public void declineRequest(School school, Request r) {
    	r = getRequest(school, r);
    	r.setStatus(RequestStatus.DECLINED);
    }

    /**
     * @return
     */
    public void signRequest(School school, Request r, Dean d) {
    	r = getRequest(school, r);
    	r.sign(d);
    }

}