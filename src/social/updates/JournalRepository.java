package social.updates;

import java.util.Vector;

import utilities.logging.LoggerProvider;

import java.io.Serializable;

/**
 * @author nurs
 */
public class JournalRepository implements Serializable {
	private int journalId;
    /**
     * 
     */
    private Vector<Journal> journals;
	
    /**
     * Default constructor
     */
    public JournalRepository() {
    	journals = new Vector<>();
    	journalId = 0;
    }

    /**
     * Prints all news to std output
     * 
     * @return
     */
    public void displayJournals() {
        for (Journal j : journals) {
        	System.out.println(j + "\n");
        }
    }

    /**
     * Adds a journals to the repository
     * 
     * @param 	journals	Journal instance 
     * @return
     */
    public void addJournal(Journal journal) {
    	LoggerProvider.getLogger().info("New journal added " + journal.getName());
    	journals.add(journal);;
    }

    /**
     * Removes a journal
     * 
     * @param	journal	an instance of Journal created with
     * 			minimum details (id)
     * @return	true 	if removed
     * 			false	if not remove
     */
    public boolean removeJournal(Journal journal) {
    	LoggerProvider.getLogger().warning("Journal removed " + journal.getName());
        return journals.add(journal);
    }

    /**
     * Returns journal
     * 
     * @param 	j	an instance of journal created with
     * 			minimum details (id)
     * @return	j	a concrete journal instance
     */
    public Journal getJournal(Journal j) {
        return journals.get(journals.indexOf(j));
    }
    
    /**
     * Produces an id for a new journal based on 
     * the current number of journals in the system
     * 
     * @return	journalId	a unique identifier of a journal
     */
    public int generateJournalId() {
    	journalId++;
    	return journalId;
    }


}