package users.employees;

import users.employees.Teacher;

import java.io.*;
import java.util.*;

public class TeacherRating {

    public TeacherRating() {
    }
    private static HashMap<Teacher, Vector<Double>> ratings = new HashMap<>();
    public static double getRating(Teacher t) {
    	Vector <Double> teacherRating = ratings.get(t);
    	if (ratings == null || ratings.isEmpty()) {
            System.out.println("No ratings available for teacher " + t.getFirstName() + " " + t.getLastName());
            return -1.0;
        }
    	double sum = 0;
        for (double rating : teacherRating) {
             sum += rating;
        }
        double avrg = sum / teacherRating.size();
        return Math.round(avrg * 100.0) / 100.0;
    }
    
    public static void addTeacherToRating(Teacher t) {//keep static for method in Teacher???
    	 if (!ratings.containsKey(t)) {
             ratings.put(t, new Vector<>());
             System.out.println("Teacher " + t.getFirstName() + " " + t.getLastName() +  " has been added to the rating system.");
         }
    }
    public static void displayRating() {
        System.out.println("All Teacher Ratings:");
        if (ratings.isEmpty()) {
            System.out.println("No ratings available");
        } else {
            for (Teacher t : ratings.keySet()) {
                double averageRating = getRating(t);
                System.out.printf("Teacher: " + t.getFirstName() + " " + t.getLastName() + "\nAverage Rating: " + averageRating);
            }
        }
    }

}