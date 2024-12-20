package users;

import java.io.Serializable;

import social.updates.*;
import enums.Gender;
import enums.Language;


public interface User extends Serializable {

    public boolean login(String email, String password, UserRepository userRepo);
    
    public void logout(); 

    public void changePassword(String password);

    public void selectLanguage(Language language);

    public void viewPersonalProfile();
    

    public void viewNews(NewsRepository newsRepo);

    public void addComment(NewsRepository newsRepo, Comment comment, int newsID);

    
    public void viewJournals(JournalRepository journals);
   
    public void subscribeToJournal(Journal journal);

    public void unsubscribeFromJournal(Journal journal);

    
    public void viewMessages();
    
    
    public String getFirstName();
    
    public String getLastName();

    public String getEmail();
    
    public String getPassword();
    
    public boolean getIsActive();
    
    public int getAge();
    
    public Gender getGender();
    
    public Language getPreferredLanguage();
    
    
    public void becomeResearcher();
}