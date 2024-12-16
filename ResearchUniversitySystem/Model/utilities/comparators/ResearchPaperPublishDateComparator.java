package utilities.comparators;

import java.util.Comparator;

import research.ResearchPaper;

/**
 * @author nurs
 */
public class ResearchPaperPublishDateComparator implements Comparator<ResearchPaper> {

    /**
     * Default constructor
     */
    public ResearchPaperPublishDateComparator() {
    }

    /**
     * Compares 2 research paper by publish date
     * in order: latest - first
     * 
     * @param ResearchPaper rp1 
     * @param ResearchPaper rp2 
     * @return
     */
	public int compare(ResearchPaper rp1, ResearchPaper rp2) {
		return rp2.getPublishedDate().compareTo(rp1.getPublishedDate());
	}

}