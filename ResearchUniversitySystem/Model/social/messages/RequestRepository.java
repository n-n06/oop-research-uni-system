package social.messages;


import java.util.Vector;
import java.io.Serializable;

/**
 * 
 */
public class RequestRepository implements Serializable  {

    /**
     * Default constructor
     */
    public RequestRepository() {
    }

    /**
     * 
     */
    private Vector<Request> requests;

    /**
     * @param req 
     * @return
     */
    public void addRequest(Request req) {
        // TODO implement here
        return ;
    }

    /**
     * @param request 
     * @return
     */
    public boolean removeRequest(Request request) {
        // TODO implement here
        return false;
    }

    /**
     * @param request 
     * @return
     */
    public Request getRequest(Request request) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void displayRequests() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void acceptRequest() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void declineRequest() {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void signRequest() {
        // TODO implement here
        return ;
    }

}