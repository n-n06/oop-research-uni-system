package utilities.comparators;

import java.util.Comparator;
import java.util.stream.Collectors;

import research.ResearchPaper;
/**
 * @author nurs
 */
public class ResearchPaperArticleLengthComparator implements Comparator<ResearchPaper> {

    /**
     * Default constructor
     */
    public ResearchPaperArticleLengthComparator() {
    }

    /**
     * Compares 2 research paper by length
     * i.e. number of pages
     * 
     * @param ResearchPaper rp1 
     * @param ResearchPaper rp2 
     * @return
     */
	public int compare(ResearchPaper rp1, ResearchPaper rp2) {
		return Integer.compare(rp2.getPages(), rp1.getPages());
	}

}