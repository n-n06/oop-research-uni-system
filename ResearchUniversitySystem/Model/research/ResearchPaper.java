package research;

import java.util.Vector;
import java.util.Date;

import enums.CitationFormat;
import social.updates.Journal;

/**
 * 
 */
public class ResearchPaper implements Comparable<ResearchPaper> {

    private String title;

    private Vector<Researcher> authors;

    private Journal journal;

    private Date publishDate;

    private Vector<String> references;

    private String doi = "doi:";

    private int pages;

    private int numOfCitations;
    
    
    /**
     * Default constructor
     */
    public ResearchPaper() {
    }

    public ResearchPaper(String title) {
    	this.title = title;
    	
    }

    /**
     * @param CitationFormat f
     */
    public String getCitation(CitationFormat format) {
        if (format == CitationFormat.PLAIN_TEXT) {
        	StringBuilder citation = new StringBuilder();
        	authors.stream().forEach(a -> citation.append(a.getFirstName().substring(0,0) + ". " + a.getLastName() + ", "));
        	citation.append(String.format("\"%s\", ", title));
        	citation.append(journal.getName());
        	citation.append(", " + publishDate.getYear());
        	citation.append(", " + pages);
        	citation.append(", " + doi);
        	return citation.toString();
        }
        if (format == CitationFormat.BIBTEX) {
        	return String.format(
        			"@INPROCEEDINGS{9766691, "
        			+ "\n	author={Liz-Domínguez, Martín and Llamas-Nistal, Martín and Caeiro-Rodríguez, Manuel and Mikic-Fonte, Fernando},"
        			+ "\n	booktitle={2022 IEEE Global Engineering Education Conference (EDUCON)}, "
        			+ "\n	title={LMS Logs and Student Performance: The Influence of Retaking a Course}," 
        			+ "\n	year={2022},"
        			+ "\n	volume={},"
        			+ "\n 	number={},"
        			+ "\n  	pages={1970-1974}, "
        			+ "\n   doi={10.1109/EDUCON52537.2022.9766691}}"
        			)
        }
    }

    public int getNumOfCitations() {
        // TODO implement here
        return 0;
    }
    
    public Date getPublishedDate() {
		return publishDate;
	}
    
    public int getPages() {
		return pages;
	}
    
    public Vector<Researcher> getAuthors() {
		return authors;
	}
    
    public String getDoi() {
		return doi;
	}
    
    public Journal getJournal() {
		return journal;
	}
    
    public Vector<String> getReferences() {
		return references;
	}
    
    public void setPublishDate(Date publishedDate) {
		this.publishDate = publishedDate;
	}
    
    
    /**
     * @param o 
     * @return
     */
    public int compareTo(ResearchPaper o) {
        // TODO implement here
        return 0;
    }
    

}