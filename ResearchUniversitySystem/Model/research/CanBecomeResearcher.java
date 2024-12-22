package research;

import java.io.*;
import java.util.*;

import users.User;

/**
 * 
 */
public interface CanBecomeResearcher extends User {

    /**
     * @return
     */
    public void becomeResearcher();
    
    public boolean getIsResearcher();

}