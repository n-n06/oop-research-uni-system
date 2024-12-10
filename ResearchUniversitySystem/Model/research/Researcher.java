package research;

import java.io.*;
import java.util.*;

import menuInfo.Journal;
import users.BaseDecorator;
import research.CanBecomeResearcher;

/**
 * 
 */
public class Researcher extends BaseDecorator implements Comparable<Researcher> {

    /**
     * 
     */
    private Vector<ResearchProject> researchProjects;

    /**
     * Default constructor
     */
    public Researcher() {
    }
    
    /**
     * @param user
     */
    public Researcher(CanBecomeResearcher user) {
        // TODO implement here
    }

    /**
     * @param researchProject 
     * @return
     */
    public void createResearchProject(ResearchProject researchProject) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public Vector<ResearchProject> getAllResearchProjects() {
        // TODO implement here
        return null;
    }

    /**
     * @param project 
     * @param paper 
     * @param journal 
     * @return
     */
    public void publishResearchPaper(ResearchProject project, ResearchPaper paper, Journal journal) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public void viewResearchProject() {
        // TODO implement here
        return ;
    }

    /**
     * @param comparator 
     * @return
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        // TODO implement here
        return ;
    }

    /**
     * @return
     */
    public int calculateHIndex() {
        // TODO implement here
        return 0;
    }

	@Override
	public int compareTo(Researcher o) {
		// TODO Auto-generated method stub
		return 0;
	}






}