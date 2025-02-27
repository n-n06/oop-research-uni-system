package users;

import java.io.*;
import java.util.*;

import research.CanBecomeResearcher;
import social.*;
import social.updates.Comment;
import social.updates.Journal;
import social.updates.JournalRepository;
import social.updates.NewsRepository;
import enums.Gender;
import enums.Language;
import enums.UserType;


public abstract class BaseDecorator implements User {

	private User user;

    public BaseDecorator() {
    }
	
	
    public BaseDecorator(User user) {
    	this.user = user;
    }
    
    public User getUser() {
		return user;
	}
    
    @Override
    public UserType getUserType() {
    	return user.getUserType();
    }
    
    @Override
    public String getFirstName() {
    	return user.getFirstName();
    }
    
    @Override
    public String getLastName() {
    	return user.getLastName();
    }

    @Override
    public String getFullName() {
    	return user.getFullName();
    }
    
    @Override
    public int getUserId() {
    	return user.getUserId();
    }
    
    @Override
    public String getEmail() {
    	return user.getEmail();
    }
    
    @Override
    public String getPassword() {
    	return user.getPassword();
    }
    
    @Override
    public boolean getIsActive() {
    	return user.getIsActive();
    }
    
    public boolean login(String email, String password, UserRepository userRepo) {
    	return user.login(email, password, userRepo);
    }
    
    public void logout() {
    	user.logout();
    }

    public void changePassword(String password) {
        user.changePassword(password);
    }

    public void selectLanguage(Language language) {
        user.selectLanguage(language);;
    }

    
    public void viewPersonalProfile() {
        user.viewPersonalProfile();
    }
    
    
    public void viewNews(NewsRepository newsRepo) {
        user.viewNews(newsRepo);
    }

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID) {
        user.addComment(newsRepo, comment, newsID);
    }
    

    public void viewJournals(JournalRepository journals) {
        user.viewJournals(journals);
    }

    public void subscribeToJournal(Journal journal) {
        user.subscribeToJournal(journal);
    }

    public void unsubscribeFromJournal(Journal journal) {
        user.unsubscribeFromJournal(journal);
    }
    
    
    public void viewMessages() {
        user.viewMessages();
    }
    
    
	@Override
	public int getAge() {
		return user.getAge();
	}

	@Override
	public Gender getGender() {
		return user.getGender();
	}

	@Override
	public Language getPreferredLanguage() {
		return user.getPreferredLanguage();
	}

}
