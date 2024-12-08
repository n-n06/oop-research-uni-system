package users;

import menuInfo.*;
import enums.Language;


public interface User {

    public boolean login(String email, String password);

    public void changePassword(String password);

    public void selectLanguage(Language language);

    public void viewNews(NewsRepository newsRepo);

    public void addComment(News news, Comment comment);

    public void viewPersonalProfile();

    public void viewJournals(JournalsRepository journals);
   
    public void subscribeToJournal(Journal journal);

    public void unsubscribeFromJournal(Journal journal);

    public void accessResearcherAccount();

    public void becomeResearcher();

}