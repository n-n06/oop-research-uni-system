package research;

import java.util.Vector;
import java.io.*;

import utilities.exceptions.InvalidSupervisorException;


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
	
    /**
     * Default constructor
     */
	public ResearchRepository() {
		researchProjects = new Vector<>();
		researchers = new Vector<>();
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
		researchProjects.add(project);
	}
	
	public boolean removeResearchProject(ResearchProject project) {
		return researchProjects.remove(project);
	}
	
	
	//Team management
	public void assignSupervisor(ResearchProject project, Researcher r) throws InvalidSupervisorException {
		if (r.calculateHIndex() < 3) {
			throw new InvalidSupervisorException(r.getFirstName() + " " + r.getLastName() + " cannot be assigned as a supervisor.");
		}
		project.setSupervisor(r);
	}
}
