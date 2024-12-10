package menuInfo;

import java.util.Vector;

/**
 * @author nurs
 */
public class JournalRepository {
    /**
     * 
     */
    private Vector<Journal> journals;
	
    /**
     * Default constructor
     */
    public JournalRepository() {
    }

    /**
     * Prints all news to std output
     * 
     * @return
     */
    public void displayAllNews() {
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