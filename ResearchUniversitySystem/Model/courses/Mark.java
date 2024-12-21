package courses;

import java.util.Objects;
import java.util.Vector;

/**
 * @author eva
 */

public class Mark {
    private Vector<Double> firstAttMarks = new Vector<>();
    private Vector<Double> secondAttMarks = new Vector<>();
    
    private double firstAttestation = 0;
    private double secondAttestation = 0;
    private double finalExam = 0;

    public Mark() {}

    public double sumFirstAttestation() {
        double sum = 0;
        for (double mark : firstAttMarks) {
            sum += mark;
        }
        return sum;
    }

    public void setFirstAttestation(Double hiddenPoints) {
	    if (hiddenPoints != null && hiddenPoints > 0) {
	        firstAttestation = sumFirstAttestation() + hiddenPoints;
	    } else {
	        firstAttestation = sumFirstAttestation();
	    }
	
	    if (firstAttestation > 60) {
	        firstAttestation = 60;
	    }
    }

    public double sumSecondAttestation() {
        double sum = 0;
        for (double mark : secondAttMarks) {
            sum += mark;
        }
        return sum;
    }

    public void setSecondAttestation(Double hiddenPoints) {
	    if (hiddenPoints != null && hiddenPoints > 0) {
	        secondAttestation = sumSecondAttestation() + hiddenPoints;
	        if (secondAttestation + firstAttestation > 60) {
	            secondAttestation = 60 - firstAttestation;
	        }
	    } else {
	        secondAttestation = sumSecondAttestation();
	    }
    }
    
    public double getFinalExamMark() {
		return finalExam;
	}

    public void setFinalExamMark(double finalExam) {
    	checkValidMark(finalExam);
        if (finalExam > 40) {
            this.finalExam = 40;
        } 
        else {
        	this.finalExam = finalExam;
        }
    }
	
    public double getFirstAttestation() {
    	return firstAttestation;
    }
    
    public double getSecondAttestation() {
    	return secondAttestation;
    }
    
    public double getFinalExam() {
    	return finalExam;
    }

    public double getOverallPoints() {
        return firstAttestation + secondAttestation + finalExam;
    }

    public String getGPALetter() {
        GPA gpa = new GPA(getOverallPoints(), finalExam, firstAttestation, secondAttestation);
        return gpa.getLetter();
    }

    public double getGPANumeric() {
        GPA gpa = new GPA(getOverallPoints(), finalExam, firstAttestation, secondAttestation);
        return gpa.getNumericValue();
    }

    private void checkValidMark(double mark) {
		if (mark < 0) {
			throw new IllegalArgumentException("Mark must be positive, but received: " + mark);
		}
    }

    public void addFirstAttestationMark(double mark) {
    	checkValidMark(mark);
        firstAttMarks.add(mark);
    }

    public void addSecondAttestationMark(double mark) {
    	checkValidMark(mark);
        secondAttMarks.add(mark);
    }
    
    @Override
	public String toString() {
		return String.format("1st att: %.2f", sumFirstAttestation()) 
				+ String.format(", 2nd att: %.2f", sumSecondAttestation()) 
				+ String.format(", Final: %.2f", finalExam)
				+ String.format("\tOverall: %.2f", getOverallPoints());
	}
    
    @Override
	public boolean equals(Object o) {
    	if (o == null) return false;
    	if (this == o) return true;
    	if (this.getClass() != o.getClass()) return false;
    	Mark m = (Mark) o;
    	
		return this.firstAttestation == m.finalExam &&
				this.secondAttestation == m.secondAttestation &&
				this.finalExam == m.finalExam;
	}
    
    @Override
	public int hashCode() {
		return Objects.hash(firstAttestation, secondAttestation, finalExam);
	}
}
