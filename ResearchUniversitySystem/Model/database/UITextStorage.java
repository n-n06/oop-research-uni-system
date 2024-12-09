package database;

import java.io.Serializable;
import java.util.HashMap;

import users.UserRepository;
import enums.Language;

/**
 * 
 */
public class UITextStorage implements Serializable {
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