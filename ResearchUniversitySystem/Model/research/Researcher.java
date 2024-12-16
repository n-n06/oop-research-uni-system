package research;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import menuInfo.Journal;
import users.BaseDecorator;
import research.CanBecomeResearcher;

/**
 * 
 */
public class Researcher extends BaseDecorator implements Comparable<Researcher> {
	
	private CanBecomeResearcher user;
	
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
    	this();
        this.user = user;
    }

    /**
     * @param researchProject 
     * @return
     */
    public void createResearchProject(ResearchProject researchProject) {
        researchProjects.add(researchProject);
    }

    /**
     * @return
     */
    public Vector<ResearchProject> getAllResearchProjects() {
        return researchProjects;
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
     * @return
     */
    public void viewResearchProject(ResearchProject project) {
    	System.out.println(researchProjects.get(researchProjects.indexOf(project)));
    }

    /**
     * @param comparator 
     * @return
     */
    public void printAllResearchPapers(Comparator<ResearchPaper> comparator) {
        researchProjects.stream()
        	.flatMap(rp->rp.getResearchPapers().stream())
        	.sorted(comparator)
        	.forEach(System.out::println);
    }

    /**
     * @return
     */
    public int calculateHIndex() {
    	List<Integer> citationList = researchProjects.stream()
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