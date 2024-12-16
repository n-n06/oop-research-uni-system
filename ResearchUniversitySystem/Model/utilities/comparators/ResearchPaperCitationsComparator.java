package utilities.comparators;

import java.util.Comparator;

import research.ResearchPaper;

/**
 * @author nurs
 */
public class ResearchPaperCitationsComparator implements Comparator<ResearchPaper> {

    /**
     * Default constructor
     */
    public ResearchPaperCitationsComparator() {
    }

    /**
     * Compares 2 research paper by num of citations
     * 
     * @param ResearchPaper r1 
     * @param ResearchPaper r2 
     * @return 
     */
	public int compare(ResearchPaper rp1, ResearchPaper rp2) {
		return Integer.compare(rp2.getNumOfCitations(), rp1.getNumOfCitations());
	}

}