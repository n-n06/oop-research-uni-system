package research;

import java.util.Vector;
import java.util.Date;

import menuInfo.Journal;
import enums.CitationFormat;

/**
 * 
 */
public class ResearchPaper implements Comparable {

    /**
     * Default constructor
     */
    public ResearchPaper() {
    }

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private Vector<Researcher> authors;

    /**
     * 
     */
    private Journal journal;

    /**
     * 
     */
    private Date publishedDate;

    /**
     * 
     */
    private Vector<String> citations;

    /**
     * 
     */
    private String doi;

    /**
     * 
     */
    private int pages;

    /**
     * 
     */
    private String plainTextCitation;

    /**
     * 
     */
    private String bibtexCitation;

    /**
     * 
     */
    private int numOfCitations;

    /**
     * @param CitationFormat f
     */
    public void getCitation(CitationFormat format) {
        // TODO implement here
    }

    /**
     * @param o 
     * @return
     */
    public int compareTo(ResearchPaper o) {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int getNumOfCitations() {
        // TODO implement here
        return 0;
    }
    
    public Date getPublishedDate() {
		return publishedDate;
	}
    
    public int getPages() {
		return pages;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}