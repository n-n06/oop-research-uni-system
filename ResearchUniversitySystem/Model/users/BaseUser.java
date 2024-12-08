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


    public BaseUser() {
    }

    private String userID;
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
    
    public boolean getIsActive() {
        return false;
    }


    public void selectLanguage(Language language) {
        return ;
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

    public boolean login(String email, String password) {
        return false;
    }

    public void changePassword(String password) {
        return;
    }

}