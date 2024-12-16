package users.students;
import java.util.Objects;
//
import java.util.Vector;

import database.Database;

/**
 * @author eva
 */
public class StudentOrganization implements Comparable<StudentOrganization> {
	private int organizationId;
	private String name;
	private String description;
	private Student head;
	private Vector<Student> members;

	{
		organizationId = Database.instance.generateOrganizationId();
	}
	
    /**
     * Default constructor
     */
    public StudentOrganization(String name, Student head) {
    	this.name = name;
    	this.head = head;
    }
    
    public void changeHead(Student newHead) {
    	this.head = newHead;
    }
    
    public void addStudent(Student s) {
    	members.add(s);
    }
    
    public void removeStudent(Student s) {
    	members.remove(s);
    }
    
    public String getDescription() {
		return description;
	}
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public void listAllMembers() {
    	members.stream().forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));
    }
    
    @Override
    public String toString() {
    	return "👥Organization: " + name + "\nAbout us: " + description + "\nMember count: " + members.size();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) return true;
    	if (obj == null) return false;
    	if (this.getClass() != obj.getClass()) return false;
    	
    	StudentOrganization so = (StudentOrganization) obj;
    	return so.organizationId == this.organizationId;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(name, description, organizationId);
    }

	@Override
	public int compareTo(StudentOrganization o) {
		return -1 * Integer.compare(this.members.size(), o.members.size());
	}
    
    
    
    /**
     * @return
     */
    // problem: manage exiting properly
//    public void manageMembers() {
//    	Scanner in = new Scanner(System.in);
//    	System.out.println("You entered the management system of " + name + ".");
//    	System.out.println("Press '1' to see all students\n" +
//    			           "Press '2' to remove a student\n" +
//    					   "Press '3' to add a student\n" + 
//    			           "Press '4' to change the head of organisation\n" +
//    					   "Press '0' to exit the management system.");
//    	
//    	int operator = in.nextInt();
//    	if (operator > 4) {
//    		System.out.println("Invalid Command!");
//    		
//    	}
//    	
//    	switch(operator) {
//    	case 1:
//    		listAllMembers();
//    		break;
//    	case 2:
//    		removeStudent(s);
//    		break;
//    	case 3: 
//    		addStudent(s);
//    		break;
//    	case 4: 
//    		changeHead(s);
//    		break;
//    	//add default case
//    	}
//    	
//        /* 1 - see all users
//    	   2 - remove user by their id
//    	   3 - add user by their id
//    	   0 - exit */
//    	in.close();
//    }

}
