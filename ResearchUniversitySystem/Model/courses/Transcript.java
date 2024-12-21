package courses;

import users.students.Student;

/**
 * 
 */
public class Transcript {
	private final Student student;

    /**
     * Default constructor
     */
    public Transcript(Student student) {
    	this.student = student;
    }
    
    public void displayTranscript() {
    	System.out.println("Transcript for " + student.getFullName());
    	System.out.println("----------------------------------");
    	
    	double sumGPA = 0;
    	int courseCount = 0;
    	
    	for (Course course : student.getCompletedCourses()) {
    		Mark studentMark = course.getGradeBook().get(student);
    		System.out.println("Course: " + course.getID() + course.getCourseName());
    		System.out.println("First Attestation: " + studentMark.getFirstAttestation());
    		System.out.println("Second Attestation: " + studentMark.getSecondAttestation());
    		System.out.println("Final Exam: " + studentMark.getFinalExam() + "\n");
    		System.out.println("📜 Overall Grade: " + studentMark.getOverallPoints());
    		System.out.println("🤌 Letter GPA: " + studentMark.getGPALetter());
    		System.out.println("🤌 Numeric GPA: " + studentMark.getGPANumeric() + "\n");
    		
    		sumGPA += studentMark.getGPANumeric();
    		courseCount++;
    	}
    	
    	if (courseCount > 0) {
            double overallGPA = sumGPA / courseCount;
            System.out.println("Overall GPA: " + String.format("%.2f", overallGPA));
        } else {
            System.out.println("💀 No completed courses to calculate GPA! 💀");
        }

    }

}
