package utilities.comparators;

import java.util.Comparator;
import users.students.Student;

/**
 * Comparator to sort Student objects by their GPA in ascending order
 * @author eva
 */

public class StudentGpaComparator implements Comparator<Student>  {

    /**
     * Compares two Student objects numerically by their GPA
     *
     * @param s1 - the first student
     * @param s2 - the second student
     * @return a negative integer, zero, or a positive integer as the GPA of s1
     *         is less than, equal to, or greater than the GPA of s2
     */
	
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.getGPA().getNumericValue(), s2.getGPA().getNumericValue()); // sorting in ascending order: 0.17, 1.15, ...
    }
    
}
