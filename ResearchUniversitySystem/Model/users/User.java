package users;

import menuInfo.*;
import enums.Language;


public interface User {

    public boolean login(String email, String password);

    public void changePassword(String password);

    public void selectLanguage(Language language);

    public void viewNews(NewsRepository newsRepo);

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID);

    public void viewPersonalProfile();
    
    public String getUserEmail();
    
    public String getPassword();

    public boolean getIsActive();
    
    public void viewJournals(JournalsRepository journals);
   
    public void subscribeToJournal(Journal journal);

    public void unsubscribeFromJournal(Journal journal);

    public void accessResearcherAccount();

    public void becomeResearcher();

}