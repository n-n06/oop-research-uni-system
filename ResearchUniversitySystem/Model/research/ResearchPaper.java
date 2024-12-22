package research;

import java.util.Vector;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import enums.CitationFormat;
import social.updates.Journal;



/**
 * 
 */
public class ResearchPaper implements Comparable<ResearchPaper> {

    private String title;

    private Vector<Researcher> authors;

    private Journal journal;

    private Date publishDate; //not directly set

    private Vector<ResearchPaper> references;

    private String doi = "doi:10.1000/"; //MLA format because we used it in MPGE) automaticlly generatedt

    private int pages;

    private int numOfCitations;//not directly set
    
    
    public ResearchPaper() {
    	doi = UUID.randomUUID().toString().substring(0,8);
    } 
    
    public ResearchPaper(String doi) {
    	this.doi = doi; //only for comparisons
    }


    public ResearchPaper(String title, Vector<Researcher> authors) {
        this();
        this.authors = authors;
    }

    public ResearchPaper(String title, Vector<Researcher> authors, Journal journal) {
        this(title, authors);
        this.journal = journal;
    }

    public ResearchPaper(String title, Vector<Researcher> authors, int pages) {
    	this(title, authors);
    	this.pages = pages;
    }
    
    public ResearchPaper(String title, Vector<Researcher> authors, Journal journal, int pages) {
    	this(title, authors, journal);
    	this.pages = pages;
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
        else {
        	String authorNames = String.join(", ", authors.stream().map(a->a.getFullName()).collect(Collectors.toList()));
        	return String.format(
        			"@INPROCEEDINGS{9766691, "
        			+ "\n	author={%s},"
        			+ "\n	booktitle={%s}, "
        			+ "\n	title={%s}," 
        			+ "\n	year={%d},"
        			+ "\n	volume={},"
        			+ "\n 	number={},"
        			+ "\n  	pages={%d}, "
        			+ "\n   doi={%s}}"
        			+ "\n	references={%s}",
        			authorNames, journal.getName(), title, 
        			publishDate.getYear(), pages, doi, 
        			references.stream().map(paper->paper.getCitation(CitationFormat.PLAIN_TEXT)).collect(Collectors.toList()).toString());
        }
    }

    public int getNumOfCitations() {
        return numOfCitations;
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
    
    public Vector<ResearchPaper> getReferences() {
		return references;
	}
    
    public void setPublishDate(Date publishedDate) {
		this.publishDate = publishedDate;
	}
    
    public void setDoi(String doi) {
		this.doi = doi;
	}
    
    public void setJournal(Journal journal) {
		this.journal = journal;
	}
    
    public void setPages(int pages) {
		this.pages = pages;
	}
    
    public String cite() {
    	numOfCitations++;
    	return getCitation(CitationFormat.PLAIN_TEXT);
    }
    
    public void addReference(ResearchPaper paper) {
    	references.add(paper);
    	System.out.println("📑You have cited: " + paper.cite());
	}
    
    public void removeReference(ResearchPaper paper) {
    	ResearchPaper paperToRemove = references.get(references.indexOf(paper));
    	references.remove(paper);
    	System.out.println("📑You have uncited: " + paperToRemove.getCitation(CitationFormat.PLAIN_TEXT));
    }
    
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	ResearchPaper paper = (ResearchPaper) obj;
    	return this.doi.equals(paper.doi);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(title, authors, journal, publishDate, doi, pages, numOfCitations);
    }
    
    @Override
    public String toString() {
    	return getCitation(CitationFormat.BIBTEX);
    }
    
    /**
     * @param o 
     * @return
     */
    public int compareTo(ResearchPaper paper) {
        return -1 * Integer.compare(this.getNumOfCitations(), paper.getNumOfCitations());
    }
    

}