package users;

import java.io.*;
import java.util.*;

import enums.Gender;
import enums.Language;
import menuInfo.*;
import research.ResearchProject;
import research.Researcher;
import research.CanBecomeResearcher;



/**
 * 
 */
public abstract class BaseUser implements CanBecomeResearcher, User {

    /**
     * Default constructor
     */
    public BaseUser() {
    }

    /**
     * 
     */
    private String userID;

    /**
     * 
     */
    private String firstName;

    /**
     * 
     */
    private String lastName;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private boolean isActive;

    /**
     * 
     */
    private Gender gender;

    /**
     * 
     */
    public String login;

    /**
     * 
     */
    private int age;

    /**
     * 
     */
    private Language preferredLanguage;

    /**
     * 
     */
    private boolean isResearcher;

    /**
     * @return
     */
    public boolean getIsActive() {
        // TODO implement here
        return false;
    }

    /**
     * @param Language 
     * @return
     */
    public void selectLanguage(Language language) {
        // TODO implement here
        return ;
    }

    /**
     * @param newsRepo 
     * @return
     */
    public void viewNews(NewsRepository newsRepo) {
        // TODO implement here
        return;
    }

    /**
     * @param news 
     * @param comment 
     * @return
     */
    public void addComment(News news, Comment comment) {
        // TODO implement here
        return;
    }

    /**
     * I am not sure about this
     * @return
     */
    public void viewPersonalProfile() {
        // TODO implement here
        return;
    }

    /**
     * @param journals 
     * @return
     */
    public void viewJournals(JournalsRepository journals) {
        // TODO implement here
        return;
    }

    /**
     * @param journal 
     * @return
     */
    public void subscribeToJournal(Journal journal) {
        // TODO implement here
        return;
    }

    /**
     * @param journal 
     * @return
     */
    public void unsubscribeFromJournal(Journal journal) {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void accessResearcherAccount() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void becomeResearcher() {
        // TODO implement here
        return;
    }

    /**
     * @param email 
     * @param password 
     * @return
     */
    public boolean login(String email, String password) {
        // TODO implement User.login() here
        return false;
    }

    /**
     * @param password 
     * @return
     */
    public void changePassword(String password) {
        // TODO implement User.changePassword() here
        return;
    }

}