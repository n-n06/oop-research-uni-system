package users;

import java.io.*;
import java.util.*;

import database.Database;
import enums.Gender;
import enums.Language;
import enums.UserType;
import social.updates.*;
import utilities.logging.LoggerProvider;
import research.ResearchProject;
import research.Researcher;
import research.CanBecomeResearcher;


public abstract class BaseUser implements User, Serializable {
	private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive = false;;
    private Gender gender;
    private int age;
    private Language preferredLanguage;

    
    public BaseUser() {
    	userId = Database.instance.getUsersRepo().generateUserId();
    }
    
	public BaseUser(String firstName, String lastName, int age, Gender gender) {
		this();
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = generateEmail(firstName, lastName);
	    this.age = age;
	    this.gender = gender;
	}
	
	private String generateEmail(String firstName, String lastName) {
		return firstName.toLowerCase().strip().substring(0, 1) + lastName.toLowerCase().strip() + "@kbtu.kz";
	}
	
    public boolean login(String email, String password, UserRepository userRepo) {
        if (userRepo.login(email, password)) {
        	this.isActive = true;
        	return true;
        };
        return false;
    }
    
    public void logout() {
    	this.isActive = false;
    }
    
    public int getUserId() {
		return userId;
    }
    
    public boolean getIsActive() {
        return isActive;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public int getAge() {
		return age;
	}
    
    public Gender getGender() {
		return gender;
	}

    public String getFullName() {
		return firstName + " " + lastName;
	}	
    
    public Language getPreferredLanguage() {
		return preferredLanguage;
	}
    
    public String getPassword() {
    	return password;
    }
    
    public void setEmail(String email) {
		this.email = email;
	}

    
    //Personal functions
    public void selectLanguage(Language language) {
        this.preferredLanguage = language;
    }
    

    public void changePassword(String password) {
    	LoggerProvider.getLogger().info(getEmail() + " has updated their password!");
    	this.password = Database.instance.getUsersRepo().hashPassword(password);
    }

    public void viewPersonalProfile() {
        System.out.println(toString() + "\n");
    }
    
    //News stuff
    public void viewNews(NewsRepository newsRepo) {
        System.out.println("News:");
        newsRepo.displayAllNews();
    }

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID) {
    	LoggerProvider.getLogger().info(getEmail() + " left a comment under news with id: " + newsID);
        newsRepo.getNews(new News(newsID)).addComment(comment);
    }

    //Journal stuff
    public void viewJournals(JournalRepository journals) {
        journals.displayJournals();
    }

    public void subscribeToJournal(Journal journal) {
    	LoggerProvider.getLogger().info(getEmail() + " has subscribed to " + journal.getName());
        journal.addSubscriber(this);
    }

    public void unsubscribeFromJournal(Journal journal) {
    	LoggerProvider.getLogger().info(getEmail() + " has unsubscribed from " + journal.getName());
        journal.removeSubscriber(this);
    }

    //Social stuff
    @Override
    public void viewMessages() {
    	Database.instance.getMessageRepo().viewMessages(this);
    }
    


    
    @Override
    public String toString() {
    	return "Email: " + email + "\nFirst name: " + firstName + "\nLast name: " + lastName 
    			+ "\nGender: " + gender + "\nAge: " + age;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	BaseUser u = (BaseUser) obj;
    	return this.email == u.email;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(userId, firstName, lastName, age, gender, email);
    }

}
