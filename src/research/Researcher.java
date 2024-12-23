package research;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import database.Database;
import enums.School;
import social.updates.Journal;
import users.BaseDecorator;
import users.User;


/**
 * 
 */
public class Researcher extends BaseDecorator implements Comparable<Researcher> {
	
	private CanBecomeResearcher user;
	

    /**
     * Default constructor
     */
    public Researcher() {
    	Database.instance.getResearchRepo().addResearcher(this);
    }
    
    /**
     * @param user
     */
    public Researcher(CanBecomeResearcher user) {
    	this();
        this.user = user;
    }
    
    public School getSchool() {
    	return user.getSchool();
    }
    
    public void setSchool(School s) {
    	user.setSchool(s);
    }


    /**
     * @param researchProject 
     * @return
     */
    public void createResearchProject(ResearchProject researchProject) {
    	Database.instance.getResearchRepo().addResearchProject(researchProject);
    }
    
    private List<ResearchProject> getOwnResearchProjects() {
    	return Database.instance.getResearchRepo().getAllResearchProjects()
        		.stream()
        		.filter(rp -> rp.getResearchTeam().contains(this))
        		.collect(Collectors.toList());
    }

    public void viewOwnResearchProjects() {
    	List<ResearchProject> projects = getOwnResearchProjects();
    	System.out.println("🗃Projects:");
    	projects.stream().forEach(p->System.out.println(p));
    }

    /**
     * @param project 
     * @param paper 
     * @param journal 
     * @return
     */
    public void publishResearchPaper(ResearchProject project, ResearchPaper paper, Journal journal) {
        journal.publishArticle(
        		project.getResearchPapers().get(project.getResearchPapers().indexOf(paper))
        		);
    }
    
    public List<ResearchPaper> getAllResearchPapers() {
    	List<ResearchPaper> papers = new ArrayList<>();
    	getOwnResearchProjects().stream().forEach(rp->rp.getResearchPapers().forEach(paper->papers.add(paper)));
    	return papers;
    }
    
    public void printAllResearchPapers() {
    	getAllResearchPapers().stream()
        	.forEach(System.out::println);
    }

    /**
     * @param comparator 
     * @return
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
    	getAllResearchPapers().stream()
        	.sorted(comparator)
        	.forEach(System.out::println);
    }
    
    public int getNumOfCitations() {
    	return getAllResearchPapers().stream()
    		.mapToInt(rp->rp.getNumOfCitations())
    		.sum();
    }

    /**
     * @return
     */
    public int calculateHIndex() {
    	List<Integer> citationList = 
    			getOwnResearchProjects().stream()
    			.flatMap(rp->rp.getResearchPapers().stream())
    			.map(paper -> paper.getNumOfCitations())
    			.sorted()
    			.collect(Collectors.toList());
        int hIndex = 0;
        int n = citationList.size();
        
        while (hIndex < n) {
            if (citationList.get(n - 1 - hIndex) > hIndex) {
                hIndex++;
            } else {
                break;
            }
        }
        return hIndex;
    }

	@Override
	public int compareTo(Researcher o) {
		return -1 * Integer.compare(this.calculateHIndex(), o.calculateHIndex());
	}


	@Override
	public String toString() {
		return "🧑‍🔬Researcher at " + user.getSchool() + "\n"
				+ super.toString()
				+ String.format("\nTakes part in %d research projects", getOwnResearchProjects().size())
				+ "\nH-index: " + calculateHIndex();
	}


}