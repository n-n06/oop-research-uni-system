package courses;

import users.students.Student;

/**
 * 
 */
public class Transcript {
	private final Student student;
	private double overallGPA;

    /**
     * Default constructor
     */
    public Transcript(Student student) {
    	this.student = student;
    	this.overallGPA = 0.0;
    }
    
    public double getOverallGPA() { // return gpa for student
    	calculateOverallGPA();
    	return overallGPA;
    }
    
    private void calculateOverallGPA() { // keep gpa updated
        double sumGPA = 0;
        int courseCount = 0;

        for (Course course : student.getCompletedCourses()) {
            Mark studentMark = course.getGradeBook().get(student);
            sumGPA += studentMark.getGPANumeric();
            courseCount++;
        }
        
        overallGPA = (courseCount > 0) ? sumGPA / courseCount : 0.0;
    }
    
    public void displayTranscript() { // print overall credits (taken and counted), completed courses + gpa, and courses in progress
        System.out.println("Transcript for " + student.getFullName() + " " + student.getUserId());
        System.out.println("----------------------------------");

        int courseCount = 0;
        int creditsTaken = 0;
        int creditsCount = 0;

        System.out.println("--------------- COMPLETED COURSES: ---------------");
        for (Course course : student.getCompletedCourses()) {
            Mark studentMark = course.getGradeBook().get(student);
            System.out.println("Course: " + course.getID() + course.getCourseName());
            System.out.println("First Attestation: " + studentMark.getFirstAttestation());
            System.out.println("Second Attestation: " + studentMark.getSecondAttestation());
            System.out.println("Final Exam: " + studentMark.getFinalExam() + "\n");
            System.out.println("📜 Overall Grade: " + studentMark.getOverallPoints());
            System.out.println("🤌 Letter GPA: " + studentMark.getGPALetter());
            System.out.println("🤌 Numeric GPA: " + studentMark.getGPANumeric() + "\n");
            if (studentMark.getGPALetter().equals("F")) {
                System.out.println("🚫 You need to retake this course! Credits are not taken, but counted. ⛔️");
                creditsCount += course.getOverallCredits();
            } else if (studentMark.getGPALetter().equals("FX")) {
                System.out.println("🚫 You need to complete final examination again! Credits are taken. ✅");
                creditsTaken += course.getOverallCredits();
            } else {
                creditsTaken += course.getOverallCredits();
            }

            courseCount++;
        }

        creditsCount += creditsTaken;

        System.out.println("--------------- COURSES IN PROGRESS: ---------------");
        for (Course course : student.getCurrentCourses()) {
            Mark studentMark = course.getGradeBook().get(student);
            System.out.println("Course: " + course.getID() + course.getCourseName());
            System.out.println("First Attestation: " + studentMark.getFirstAttestation());
            System.out.println("Second Attestation: " + studentMark.getSecondAttestation());
            System.out.println("Final Exam: " + studentMark.getFinalExam() + "\n");
            if (studentMark.getFirstAttestation() != 0.0 && studentMark.getSecondAttestation() != 0.0 && studentMark.getGPALetter().equals("F")) {
                System.out.println("⚠️ For your own sanity, we advise you to retake this course before final examination and before it is moved to 'COMPLETED COURSES'.");
            }

            creditsCount += course.getOverallCredits();
        }

        if (courseCount > 0) {
            calculateOverallGPA();
            System.out.println("Overall GPA: " + String.format("%.2f", overallGPA));
            System.out.println("Completed courses: " + courseCount);
        } else {
            System.out.println("💀 No completed courses to calculate GPA! 💀");
        }

        System.out.println("Credits taken: " + creditsTaken);
        System.out.println("Credits counted: " + creditsCount);
    }
}
