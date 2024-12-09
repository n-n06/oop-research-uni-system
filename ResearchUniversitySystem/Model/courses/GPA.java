package courses;

import enums.Grade;
import java.util.*;

/*
 * @author eva
 */

public class GPA implements Comparable<GPA> {
    private Grade grade;

    public GPA(double overallPoints, double finalExam, double firstAttestation, double secondAttestation) {
	// gracefully handling F case:
        if (finalExam < 9.5 || firstAttestation + secondAttestation < 30) {
            this.grade = Grade.F;
        }
	// gracefully handling FX case:
        else if (finalExam < 20) {
            this.grade = Grade.FX;
        }
        else {
            this.grade = Grade.fromPoints(overallPoints);
        }
    }

    public String getLetter() {
        return grade.getLetter();
    }

    public double getNumericValue() {
        return grade.getNumericValue();
    }

    @Override
    public int compareTo(GPA other) {
        return Double.compare(this.getNumericValue(), other.getNumericValue());
    }

    @Override
    public String toString() {
        return "GPA: " + getNumericValue() + ", Letter Grade: " + getLetter();
    }

    @Override
    public boolean equals(Object o) {
    	if (o == null) return false;
    	if (this == o) return true;
    	if (this.getClass() != o.getClass()) return false;
    	
    	GPA g = (GPA) o;
    	return this.grade.equals(g.grade);
    	return super.equals(o);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(getNumericValue());
    }
}
