package research;

import users.User;
import enums.School;

/**
 * 
 */
public interface CanBecomeResearcher extends User {

    public void becomeResearcher();
    
    public boolean getIsResearcher();
    
    public School getSchool();
    
    public void setSchool(School s);

}