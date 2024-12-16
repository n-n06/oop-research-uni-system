package research;

import java.util.Vector;

/**
 * 
 */
public class ResearchProject {

    /**
     * Default constructor
     */
    public ResearchProject() {
    }

    /**
     * 
     */
    private String topic;

    /**
     * 
     */
    private Vector<ResearchPaper> publishedPapers;

    /**
     * 
     */
    private Vector<Researcher> team;

    /**
     * 
     */
    private Researcher supervisor;

    /**
     * @return
     */
    public Vector<ResearchPaper> getResearchPapers() {
        // TODO implement here
        return publishedPapers;
    }
    
    public Vector<ResearchPaper> printResearchPapers() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Vector<Researcher> getResearchTeam() {
        // TODO implement here
        return null;
    }

    /**
     * @param supervisor 
     * @return
     */
    public void setSupervisor(Researcher supervisor) {
        // TODO implement here
        return;
    }

}