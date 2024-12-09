package database;

import java.io.Serializable;
import java.util.HashMap;

import users.UsersRepository;
import enums.Language;

/**
 * 
 */
public class UITextStorage implements Serializable {
	public UserRepository uP = new UserRepository();
    /**
     * 
     */
    private static UITextStorage instance;

    /**
     * 
     */
    private HashMap<Language,HashMap<String,String>> data;

    /**
     * 
     */
    private UITextStorage() {
        // TODO implement here
    }

    /**
     * @return
     */
    public UITextStorage read() {
        // TODO implement here
        return null;
    }

}