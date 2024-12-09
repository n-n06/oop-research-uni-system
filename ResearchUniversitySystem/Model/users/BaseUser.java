package users;

import java.io.*;
import java.util.*;

import enums.Gender;
import enums.Language;
import menuInfo.*;
import research.ResearchProject;
import research.Researcher;
import research.CanBecomeResearcher;


public abstract class BaseUser implements CanBecomeResearcher, User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
    private Gender gender;
    public String login;
    private int age;
    private Language preferredLanguage;
    private boolean isResearcher;
    
    
	public BaseUser(String firstName, String lastName, String email, int age, Gender gender) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	    this.isActive = false;
	}
	
    public void login(String email, String password, UsersRepository userRepo) {
        if (userRepo.login(email, password)) {
//        	System.out.println("Success login")
        	this.isActive = true;
        };
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

    public void selectLanguage(Language language) {
        this.preferredLanguage = language;
    }

    public void viewNews(NewsRepository newsRepo) {
        System.out.println("News:");
        newsRepo.displayAllNews();
    }

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID) {
        newsRepo.getNews(newsID).addCommentToNews(comment);
    }


    public void viewPersonalProfile() {
        System.out.println("Email : " + email);
        System.out.println("Firstname : " + firstName);
        System.out.println("Lastname : " + lastName);
        System.out.println("Gender : " + gender);
        System.out.println("Researcher : " + isResearcher);
    }


    public void viewJournals(JournalsRepository journals) {
        journals.displayJournals();
    }

    public void subscribeToJournal(Journal journal) {
        journal.addSubscriber(this);
    }

    public void unsubscribeFromJournal(Journal journal) {
        journal.removeSubscriber(this);
    }

    public void accessResearcherAccount() {
        return;
    }

    @Override
    public void viewMessages() {
    	// TODO Auto-generated method stub
    	
    } 

    @Override
    public String getName() {
    	// TODO Auto-generated method stub
    	return null;
    }


    public void becomeResearcher() {
        this.isResearcher = true;
    }


    public void changePassword(String password) {
        this.password = password;
    }

}