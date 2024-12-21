package users.employees;

import java.io.*;
import java.util.*;

import enums.Gender;
import enums.UserType;
import social.messages.Request;
import social.messages.RequestRepository;

/**
 * 
 */
public class Dean extends Employee {

    /**
     * Default constructor
     */
    public Dean() {
    }
    
    public Dean(String firstName, String lastName, int age, Gender gender) {
    	super(firstName, lastName, age, gender);
    }

    public void viewEmployeeRequests(RequestRepository requests, Employee e) {
        // TODO implement here
        return;
    }

    public void signRequest(Request request) {
        // TODO implement here
        return ;
    }

    /**
     * @param request 
     * @return
     */
    public void rejectRequest(Request request) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void viewComplaints() {
        // TODO implement here
        return ;
    }

}