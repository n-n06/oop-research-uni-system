package utilities.comparators;

import java.util.Comparator;
import users.students.Student;

/**
 * Comparator to sort Student objects alphabetically by their names
 * @author eva
 */

public class StudentAlphabetComparator extends StudentsComparator {

    /**
     * Compares two Student objects alphabetically by their names
     *
     * @param s1 - the first student
     * @param s2 - the second student
     * @return a negative integer, zero, or a positive integer as the name of s1
     *         is lexicographically less than, equal to, or greater than the name of s2
     */
	
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getFirstName().compareTo(s2.getFirstName()); // sorting in asccending order: 'Alice', 'Bob', ...
    }
    
}
