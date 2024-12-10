package menuInfo;

import java.util.Vector;
import java.io.Serializable;

/**
 * @author nurs
 */
public class JournalRepository implements Serializable {
    /**
     * 
     */
    private Vector<Journal> journals;
	
    /**
     * Default constructor
     */
    public JournalRepository() {
    	journals = new Vector<>();
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
    


}