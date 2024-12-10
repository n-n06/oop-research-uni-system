package users.students;

import java.util.Vector;
import java.util.Scanner;

/**
 * @author eva
 */
public class StudentOrganization {
	private String name;
	private Student head;
	private Vector<Student> members;

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
    
    public void listAllMembers() {
		for (Student s : members) {
			System.out.println(s.firstName + " " + s.lastName);
		}
    }
    
    /**
     * @return
     */
    // problem: manage exiting properly
    public void manageMembers() {
    	Scanner in = new Scanner(System.in);
    	System.out.println("You entered the management system of " + name + ".");
    	System.out.println("Press '1' to see all students\n" +
    			           "Press '2' to remove a student\n" +
    					   "Press '3' to add a student\n" + 
    			           "Press '4' to change the head of organisation\n" +
    					   "Press '0' to exit the management system.")
    	
    	int operator = in.nextInt();
    	if (operator > 4) {
    		System.out.println("Invalid Command!";
    		
    	}
    	
    	switch(operator) {
    	case 1:
    		listAllMembers();
    		break;
    	case 2:
    		removeStudent(Student s);
    		break;
    	case 3: 
    		addStudent(Student s);
    		break;
    	case 4: 
    		changeHead(Student s);
    		break;
    	//add default case
    	}
    	
        /* 1 - see all users
    	   2 - remove user by their id
    	   3 - add user by their id
    	   0 - exit */
    	in.close();
    }

}
