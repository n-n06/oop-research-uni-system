package users.employees;

import java.io.*;
import java.util.*;

import database.Database;
import enums.Gender;
import enums.School;
import enums.UserType;
import social.messages.Request;
import social.messages.RequestRepository;

public class Dean extends Employee {

	School school;
	
    public Dean() {
    	
    }
    
    public Dean(String firstName, String lastName, int age, Gender gender, School school) {
    	super(firstName, lastName, age, gender);
    	this.school = school;
    }

    public void signRequest(School school, Request request) {
        Database.instance.getReqeustRepo().acceptRequest(school, request);
    }

    public void rejectRequest(School school, Request request) {
        Database.instance.getReqeustRepo().declineRequest(school, request);
    }

    public void viewRequests(School school) {
    	Database.instance.getReqeustRepo().viewRequests(school);
    }
    public void viewComplaints() {
        // TODO implement here
        return ;
    }

}