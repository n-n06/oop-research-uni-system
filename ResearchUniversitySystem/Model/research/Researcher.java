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
	
//    /**
//     * 
//     */
//    private Vector<ResearchProject> researchProjects;

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

    /**
     * @param project 
     * @param paper 
     * @param journal 
     * @return
     */
    public void publishResearchPaper(ResearchProject project, ResearchPaper paper, Journal journal) {
        journal.publishArticle(project.getResearchPapers().get(project.getResearchPapers().indexOf(journal)));
    }

    /**
     * @param comparator 
     * @return
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
    	Database.instance.getResearchRepo().getAllResearchProjects()
    		.stream()
    		.filter(rp -> rp.getResearchTeam().contains(this))
        	.flatMap(rp -> rp.getResearchPapers().stream())
        	.sorted(comparator)
        	.forEach(System.out::println);
    }

    /**
     * @return
     */
    public int calculateHIndex() {
    	List<Integer> citationList = 
    			Database.instance.getResearchRepo().getAllResearchProjects()
        		.stream()
        		.filter(rp -> rp.getResearchTeam().contains(this))
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