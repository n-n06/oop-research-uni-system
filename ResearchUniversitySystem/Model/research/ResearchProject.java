package research;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

import users.employees.*;
import utilities.exceptions.InvalidSupervisorException;
import utilities.exceptions.NotASupervisorException;
import enums.*;
import database.Database;

/**
 * 
 */
public class ResearchProject implements Serializable, Comparable<ResearchProject> {
	
	private int researchProjectId;

    private String topic;

    private Vector<ResearchPaper> publishedPapers;

    private Vector<Researcher> team;

    private Researcher supervisor;
    
    private boolean hasSupervisor = false;
	

    
    public ResearchProject() {
    	researchProjectId = Database.instance.getResearchRepo().generateResearchProjectId();
    	team = new Vector<>();
    	publishedPapers = new Vector<>();
    }
    
    public ResearchProject(int id) {
    	researchProjectId = id;
    }
    
    public ResearchProject(String topic) {
    	this();
    	this.topic = topic;
    }
    
    public ResearchProject(String topic, Vector<Researcher> team) {
    	this(topic);
    	this.team = team;
    }

    
    public String getTopic() {
		return topic;
	}
    
    public void setTopic(String topic) {
		this.topic = topic;
	}


    /**
     * @return
     */
    public Vector<ResearchPaper> getResearchPapers() {
        return publishedPapers;
    }
    
    /**
     * 
     * 
     */
    public void printResearchPapers() {
    	System.out.println("📝Research papers of this Project: \n");
        publishedPapers.stream().forEach(paper->System.out.println(paper.getCitation(CitationFormat.PLAIN_TEXT)));
    }
    
    
    /**
     * 
     * 
     */
    public void printResearchPapers(Comparator<ResearchPaper> comparator) {
    	System.out.println("📝Research papers of this Project: \n");
    	publishedPapers.stream()
    		.sorted(comparator)
    		.forEach(paper->System.out.println(paper.getCitation(CitationFormat.PLAIN_TEXT)));;
    }
    
    

    /**
     * @return
     */
    public Vector<Researcher> getResearchTeam() {
        return getResearchTeam();
    }
    
    
    /**
     * 
     * 
     */
    public void addResearcherToTeam(Researcher supervisor, Researcher member) throws NotASupervisorException {
    	if (hasSupervisor && !supervisor.equals(this.supervisor)) {
    		throw new NotASupervisorException(supervisor.getFullName() + " is not a supervisor and cannot manage the research team");
    	}
    	team.add(member);
    }
    
    
    /**
     * 
     * 
     */
    public void removeResearcherFromTeam(Researcher user, Researcher member) throws NotASupervisorException {
    	if (hasSupervisor && !user.equals(this.supervisor)) {
    		throw new NotASupervisorException(user.getFullName() + " is not a supervisor and cannot manage the research team");
    	}
    	team.remove(member);
    }
    
    /**
     * 
     * 
     */
    public Researcher getSupervisor() {
		return supervisor;
	}
    
    /**
     * 
     * 
     * @param supervisor 
     * @return
     * @throws InvalidSupervisorException 
     */
    public void setSupervisor(Researcher supervisor) throws InvalidSupervisorException {
    	if (!(supervisor.getUser() instanceof Teacher) || supervisor.calculateHIndex() < 3) {
    		throw new InvalidSupervisorException(supervisor.getFullName() + " cannot be a supervisor yet!");
    	}
    	this.hasSupervisor = true;
        this.supervisor = supervisor;
    }
    
    
    public int getCitationsOfPapers() {
    	return publishedPapers.stream().mapToInt(paper->paper.getNumOfCitations()).sum();
    }
    
    
    @Override
    public String toString() {
    	return "🔬Research project: \n" 
    			+ "Topic: " + topic + "\n"
    			+ "Members: " + team.stream().map(m->m.getFullName()).collect(Collectors.toList()) + "\n"
    			+ "Supervisor: " + (hasSupervisor ? supervisor.getFullName() : "Not assigned") + "\n"
    			+ "Papers: " + publishedPapers.stream()
    			.map(p->p.getCitation(CitationFormat.PLAIN_TEXT))
    			.collect(Collectors.joining("; ")); 
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) return false;
    	if (this == obj) return true;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	ResearchProject p = (ResearchProject) obj;
    	return this.researchProjectId == p.researchProjectId;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(topic, team, publishedPapers);
    }

	@Override
	public int compareTo(ResearchProject o) {
		return -1 * Integer.compare(this.getCitationsOfPapers(), o.getCitationsOfPapers());
	}
    
    

}