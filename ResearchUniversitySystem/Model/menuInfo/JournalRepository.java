package menuInfo;

import java.util.Vector;

/**
 * 
 */
public class JournalRepository {
    /**
     * Default constructor
     */
    public JournalRepository() {
    }

    /**
     * 
     */
    private Vector<Journal> journals;
    
    
    
    public int getJournalId() {
		return journals.size() + 1;
	}

    /**
     * @param journal 
     * @return
     */
    public void addJournal(Journal journal) {
        
        return;
    }

    /**
     * @param journal 
     * @return
     */
    public boolean removeJournal(Journal journal) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public void displayJournals() {
        // TODO implement here
        return;
    }

}