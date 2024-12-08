package users;

import java.io.*;
import java.util.*;

import menuInfo.*;
import enums.Language;

/**
 * 
 */
public abstract class BaseDecorator implements User {

    /**
     * Default constructor
     */
    public BaseDecorator() {
    }

    /**
     * 
     */

    private User user;

    /**
     * @param user
     */
    public BaseDecorator(User user) {
        // TODO implement here
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

    /**
     * @param Language 
     * @return
     */
    public void selectLanguage(Language language) {
        // TODO implement User.selectLanguage() here
        return;
    }

    /**
     * @param newsRepo 
     * @return
     */
    public void viewNews(NewsRepository newsRepo) {
        // TODO implement User.viewNews() here
        return;
    }

    /**
     * @param news 
     * @param comment 
     * @return
     */
    public void addComment(News news, Comment comment) {
        // TODO implement User.addComment() here
        return;
    }

    /**
     * @return
     */
    public void viewPersonalProfile() {
        // TODO implement User.viewPersonalProfile() here
        return;
    }

    /**
     * @param journals 
     * @return
     */
    public void viewJournals(JournalsRepository journals) {
        // TODO implement User.viewJournals() here
        return;
    }

    /**
     * @param journal 
     * @return
     */
    public void subscribeToJournal(Journal journal) {
        // TODO implement User.subscribeToJournal() here
        return;
    }

    /**
     * @param journal 
     * @return
     */
    public void unsubscribeFromJournal(Journal journal) {
        // TODO implement User.unsubscribeFromJournal() here
        return;
    }

    /**
     * @return
     */
    public void accessResearcherAccount() {
        // TODO implement User.accessResearcherAccount() here
        return;
    }

    /**
     * @return
     */
    public void becomeResearcher() {
        // TODO implement User.becomeResearcher() here
        return;
    }

}