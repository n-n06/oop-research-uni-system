package users;

import menuInfo.*;
import enums.Language;

/**
 * 
 */
public interface User {

    /**
     * @param email 
     * @param password 
     * @return
     */
    public boolean login(String email, String password);

    /**
     * @param password 
     * @return
     */
    public void changePassword(String password);

    /**
     * @param Language 
     * @return
     */
    public void selectLanguage(Language language);

    /**
     * @param newsRepo 
     * @return
     */
    public void viewNews(NewsRepository newsRepo);

    /**
     * @param news 
     * @param comment 
     * @return
     */
    public void addComment(News news, Comment comment);

    /**
     * @return
     */
    public void viewPersonalProfile();

    /**
     * @param journals 
     * @return
     */
    public void viewJournals(JournalsRepository journals);

    /**
     * @param journal 
     * @return
     */
    public void subscribeToJournal(Journal journal);

    /**
     * @param journal 
     * @return
     */
    public void unsubscribeFromJournal(Journal journal);

    /**
     * @return
     */
    public void accessResearcherAccount();

    /**
     * @return
     */
    public void becomeResearcher();

}