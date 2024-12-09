package users;

import java.io.*;
import java.util.*;

import menuInfo.*;
import enums.Language;


public abstract class BaseDecorator implements User {

	private User user;
	
	
	
    public BaseDecorator() {
    }

    

    public BaseDecorator(User user) {
    }

    public boolean login(String email, String password) {
        return false;
    }

    public void changePassword(String password) {
        return;
    }

    public void selectLanguage(Language language) {
        return;
    }


    public void viewNews(NewsRepository newsRepo) {
        return;
    }

    public void addComment(News news, Comment comment) {
        return;
    }

    public void viewPersonalProfile() {
        return;
    }

    public void viewJournals(JournalsRepository journals) {
        return;
    }

    public void subscribeToJournal(Journal journal) {
        return;
    }

    public void unsubscribeFromJournal(Journal journal) {
        return;
    }


    public void accessResearcherAccount() {
        return;
    }

    public void becomeResearcher() {
        return;
    }

}