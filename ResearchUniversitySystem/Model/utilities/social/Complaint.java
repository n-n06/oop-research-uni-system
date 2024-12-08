package utilities.social;

import enums.UrgencyLevel;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Complaint {

    /**
     * Default constructor
     */
    public Complaint() {
    }

    /**
     * 
     */
    private UrgencyLevel urgencyLevel;

    /**
     * 
     */
    private Message message;

    /**
     * 
     */
    private static Vector<Complaint> complaints;

}