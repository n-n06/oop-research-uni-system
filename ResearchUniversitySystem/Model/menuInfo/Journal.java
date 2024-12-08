package menuInfo;

import java.io.*;
import java.util.*;

import research.ResearchPaper;
import research.Researcher;
import users.User;
import enums.Language;

/**
 * 
 */
public class Journal {

    /**
     * Default constructor
     */
    public Journal() {
    }

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    public Researcher author;

    /**
     * 
     */
    private Language language;

    /**
     * 
     */
    private String topic;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Vector<ResearchPaper> articles;

    /**
     * 
     */
    private Vector<User> subscribers;

    /**
     * @return
     */
    public void displayArticles() {
        // TODO implement here
        return;
    }

    /**
     * 
     */
    public void Operation2() {
        // TODO implement here
    }

    /**
     * @param article 
     * @return
     */
    public void publishArticle(ResearchPaper article) {
        // TODO implement here
        return;
    }

    /**
     * @param article 
     * @return
     */
    public boolean removeArticle(ResearchPaper article) {
        // TODO implement here
        return false;
    }

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

    /**
     * @param user 
     * @return
     */
    public void addSubscriber(User user) {
        // TODO implement here
        return;
    }

    /**
     * @param user 
     * @return
     */
    public void removeSubscriber(User user) {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    public void notifyAllSubscribers() {
        // TODO implement here
        return;
    }

}