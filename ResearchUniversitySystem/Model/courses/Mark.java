package courses;

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
    	if (hiddenPoints != null) {	
    		firstAttestation = sumSecondAttestation() + hiddenPoints;
    	}
    	firstAttestation = sumSecondAttestation();
    }

    public double sumSecondAttestation() {
        double sum = 0;
        for (double mark : secondAttMarks) {
            sum += mark;
        }
        return sum;
    }

    public void setSecondAttestation(Double hiddenPoints) {
    	if (hiddenPoints != null) {	
    		secondAttestation = sumSecondAttestation() + hiddenPoints;
    		if (secondAttestation + firstAttestation > 60) {
    			secondAttestation = 60 - firstAttestation;
    		}
    	}
    	secondAttestation = sumSecondAttestation();
    }

    public void setFinalExamMark(double finalExam) {
        if (finalExam <= 40) {
            this.finalExam = finalExam;
        }
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

    public void addFirstAttestationMark(double mark) {
        firstAttMarks.add(mark);
    }

    public void addSecondAttestationMark(double mark) {
        secondAttMarks.add(mark);
    }
}
