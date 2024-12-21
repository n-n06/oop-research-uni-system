package courses;

import java.util.*;
import users.students.*;
/**
 *
 */
public class GradeReport {
    private final Course course;
    private double minGrade;
    private double maxGrade;
    private double averageGrade;
    private final HashMap<Student, Mark> gradeBook;


    public GradeReport(Course course) {
        this.course = course;
        this.gradeBook = course.getGradeBook();
        calculateGradeStatistics();
    }

    private void calculateGradeStatistics() {
        minGrade = Double.POSITIVE_INFINITY;
        maxGrade = Double.NEGATIVE_INFINITY;
        double totalGrade = 0;
        
        for (Map.Entry<Student, Mark> entry : gradeBook.entrySet()) {
            double grade = entry.getValue().getOverallPoints();
            totalGrade += grade;

            if (grade < minGrade) {
                minGrade = grade;
            }
            if (grade > maxGrade) {
                maxGrade = grade;
            }
        }

        if (!gradeBook.isEmpty()) {
            averageGrade = totalGrade / gradeBook.size();
        }
    }

    public double getMinGrade() {
        return minGrade;
    }

    public double getMaxGrade() {
        return maxGrade;
    }

    public double getAverageGrade() {
        return averageGrade;
    }
    
    public void updateStatistics(double newGrade) {
        if (newGrade < minGrade) {
            minGrade = newGrade;
        }
        if (newGrade > maxGrade) {
            maxGrade = newGrade;
        }
        double totalGrade = (averageGrade * gradeBook.size()) + newGrade;
        averageGrade = totalGrade / (gradeBook.size() + 1);
    }

    public void displayDistribution() {
        Map<Integer, Integer> markDistribution = new HashMap<>();

        for (Map.Entry<Student, Mark> entry : gradeBook.entrySet()) {
            int mark = (int) Math.round(entry.getValue().getOverallPoints());

            markDistribution.merge(mark, 1, Integer::sum);
        }

        System.out.println("Mark Distribution for Course: " + course.getID());
        System.out.println("----------------------------------");
        for (Map.Entry<Integer, Integer> entry : markDistribution.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " student(s)");
        }
    }

    public void displayReport() {
        System.out.println("Grade Report for Course: " + course.getID());
        System.out.println("----------------------------------");

        for (Map.Entry<Student, Mark> entry : gradeBook.entrySet()) {
            Student student = entry.getKey();
            Mark mark = entry.getValue();
            System.out.println(student.getFullName() + ": " + mark.getOverallPoints());
        }
    }
}
