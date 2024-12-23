package research;

import java.util.Vector;

import enums.School;

import java.io.*;

import utilities.comparators.ResearcherCitationNumberComparator;
import utilities.exceptions.*;
import utilities.logging.LoggerProvider;


/**
 * @author nurs
 */
public class ResearchRepository implements Serializable {
    /**
     * 
     */
	private Vector<ResearchProject> researchProjects;
    /**
     * 
     */
	private Vector<Researcher> researchers;
	
	private int researchProjectId;
	
    /**
     * Default constructor
     */
	public ResearchRepository() {
		researchProjects = new Vector<>();
		researchers = new Vector<>();
		researchProjectId = 0;
	}
	
	
	//Researcher management
	public void addResearcher(Researcher r) { 
		researchers.add(r);
	}
	
	public boolean removeResearcher(Researcher r) {
		return researchers.remove(r);
	}
	
	public Researcher getResearcher(Researcher r) {
		return researchers.get(researchers.indexOf(r));
	}
	
	//Cumulative methods
	public void viewAllResearchers() {
		System.out.println("All Researchers: ");
		researchers.stream().forEach(r->System.out.println(r + "\n"));
	}
	
	public Researcher getTopCitedResearcher() throws NoResearchersException {
		ResearcherCitationNumberComparator comp = new ResearcherCitationNumberComparator();
		return researchers.stream()
				.max(comp)
				.orElseThrow(() -> new NoResearchersException("No researchers found"));
	}
	
	public Researcher getTopCitedResearcher(School school) throws NoResearchersException {
		ResearcherCitationNumberComparator comp = new ResearcherCitationNumberComparator();
		return researchers.stream()
				.filter(r->r.getSchool() == school)
				.max(comp)
				.orElseThrow(() -> new NoResearchersException("No researchers found"));
	}
	
	
	//Projects management
	public Vector<ResearchProject> getAllResearchProjects() {
		return researchProjects;
	}
	
	public void displayResearchProjects() {
		researchProjects.stream().forEach(rp->System.out.println(rp));
	}
	
	public ResearchProject getResearchProject(ResearchProject project) {
		return researchProjects.get(researchProjects.indexOf(project));
	}
	
	public void addResearchProject(ResearchProject project) {
		LoggerProvider.getLogger().info("New Research project added " + project.getTopic());
		researchProjects.add(project);
	}
	
	public boolean removeResearchProject(ResearchProject project) {
		LoggerProvider.getLogger().warning("Research project removed " + project.getTopic());
		return researchProjects.remove(project);
	}
	

	
    /**
     * Produces an id for a new research project instance based on 
     * the current number of research projects in the system
     * 
     * @return	researchProjectId	a unique identifier of a research project
     */
    public int generateResearchProjectId() {
    	researchProjectId++;
    	return researchProjectId;
    }
}
