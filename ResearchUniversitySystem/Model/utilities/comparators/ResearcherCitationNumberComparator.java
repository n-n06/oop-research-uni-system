package utilities.comparators;

import java.util.Comparator;

import research.Researcher;
/**
 * @author nurs
 */
public class ResearcherCitationNumberComparator implements Comparator<Researcher> {
	
	public ResearcherCitationNumberComparator() {
		
	}
	
    /**
     * Compares 2 researchers by number of citations
     * 
     * @param Researcher r1 
     * @param Researcher r2 
     * @return
     */
	@Override
	public int compare(Researcher r1, Researcher r2) {
		return -1 * Integer.compare(r1.getNumOfCitations(), r2.getNumOfCitations());
	}


}
