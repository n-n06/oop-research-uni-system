package users;

import java.io.*;
import java.util.*;

import database.Database;
import enums.Gender;
import enums.Language;
import menuInfo.*;
import research.ResearchProject;
import research.Researcher;
import research.CanBecomeResearcher;


public abstract class BaseUser implements CanBecomeResearcher, User, Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password = "root";
    private boolean isActive;
    private Gender gender;
    private int age;
    private Language preferredLanguage;
    private boolean isResearcher;
    
    public BaseUser() {
    	
    }
    
	public BaseUser(String firstName, String lastName, String email) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	}
    
    
	public BaseUser(String firstName, String lastName, String email, int age, Gender gender) {
	    this(firstName, lastName, email);
	    this.isActive = false;
	}
	
    public boolean login(String email, String password, UserRepository userRepo) {
        if (userRepo.login(email, password)) {
//        	System.out.println("Success login")
        	this.isActive = true;
        	return true;
        };
        return false;
    }
    
    public void logout() {
    	this.isActive = false;
    }
    
    public boolean getIsActive() {
        return isActive;
    }
    
    public String getUserEmail() {
    	return email;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setEmail(String email) {
		this.email = email;
	}

    public void selectLanguage(Language language) {
        this.preferredLanguage = language;
    }

    public void viewNews(NewsRepository newsRepo) {
        System.out.println("News:");
        newsRepo.displayAllNews();
    }

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID) {
        newsRepo.getNews(new News(newsID)).addComment(comment);
    }


    public void viewPersonalProfile() {
        System.out.println("Email : " + email);
        System.out.println("Firstname : " + firstName);
        System.out.println("Lastname : " + lastName);
        System.out.println("Gender : " + gender);
        System.out.println("Researcher : " + isResearcher);
    }


    public void viewJournals(JournalRepository journals) {
        journals.displayJournals();
    }

    public void subscribeToJournal(Journal journal) {
        journal.addSubscriber(this);
    }

    public void unsubscribeFromJournal(Journal journal) {
        journal.removeSubscriber(this);
    }



    @Override
    public void viewMessages() {
    	Database.instance.getMessageRepo().viewMessages(this);
    } 

    @Override
    public String getName() {
    	return firstName + " " + lastName;
    }


    public void becomeResearcher() {
        this.isResearcher = true;
    }


    public void changePassword(String password) {
        this.password = password;
    }

}