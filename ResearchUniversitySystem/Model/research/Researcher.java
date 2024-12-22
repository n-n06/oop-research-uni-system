package research;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import database.Database;
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
    	
    }
    
    /**
     * @param user
     */
    public Researcher(CanBecomeResearcher user) {
    	this();
        this.user = user;
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
    
    /**
     * 
     * 
     */
    public void viewOwnResearchProjects() {
    	List<ResearchProject> projects = getOwnResearchProjects();
    	System.out.println("🗃Your projects:");
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
    
    public void printAllResearchPapers() {
    	getOwnResearchProjects().stream()
        	.flatMap(rp -> rp.getResearchPapers().stream())
        	.forEach(System.out::println);
    }

    /**
     * @param comparator 
     * @return
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
    	getOwnResearchProjects().stream()
        	.flatMap(rp -> rp.getResearchPapers().stream())
        	.sorted(comparator)
        	.forEach(System.out::println);
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
		return 0;
	}








}