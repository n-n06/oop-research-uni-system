package courses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import users.employees.Teacher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule implements Serializable {
	
    private Map<DayOfWeek, TreeMap<TimeWindow, Lesson>> schedule = new TreeMap<>();
    
    public Schedule() {
        for (DayOfWeek day : DayOfWeek.values()) {
            schedule.put(day, new TreeMap<>());
        }
    }
    
	public boolean addLesson(Lesson lesson) {
        TimeWindow time = lesson.getLessonTime();
        DayOfWeek dayOfWeek = lesson.getDayOfWeeek();
        
        TreeMap<TimeWindow, Lesson> dailySchedule = schedule.get(dayOfWeek);

        if (dailySchedule.containsKey(time)) {
        	System.out.println("Time slot is already occupied on: " + dayOfWeek + " at" + time);
            return false; 
        }

        dailySchedule.put(time, lesson);
        return true; 
    }  
	
	public boolean removeLesson(DayOfWeek day, TimeWindow timeWindow) {
        TreeMap<TimeWindow, Lesson> dailySchedule = schedule.get(day);
        if (dailySchedule != null && dailySchedule.containsKey(timeWindow)) {
            dailySchedule.remove(timeWindow);
            System.out.println("Lesson removed from " + day + " at " + timeWindow);
            return true;
        } else {
            System.out.println("No lesson found on " + day + " at " + timeWindow);
            return false;
        }
    }

    public boolean updateLesson(DayOfWeek day, TimeWindow oldTimeWindow, TimeWindow newTimeWindow) {
        TreeMap<TimeWindow, Lesson> dailySchedule = schedule.get(day);
        if (dailySchedule != null && dailySchedule.containsKey(oldTimeWindow)) {
            if (dailySchedule.containsKey(newTimeWindow)) {
                System.out.println("Cannot update: New time slot on " + day + " at " + newTimeWindow + " is already occupied.");
                return false;
            }
            Lesson lesson = dailySchedule.remove(oldTimeWindow);
            lesson.setTime(newTimeWindow); 
            dailySchedule.put(newTimeWindow, lesson);
            System.out.println("Lesson updated on " + day + " from " + oldTimeWindow + " to " + newTimeWindow);
            return true;
        } else {
            System.out.println("No lesson found on " + day + " at " + oldTimeWindow);
            return false;
        }
    }
	
	
	
	public Lesson getLesson(DayOfWeek day, TimeWindow timeWindow) {
	    TreeMap<TimeWindow, Lesson> daySchedule = schedule.get(day);
	    return (daySchedule != null) ? daySchedule.get(timeWindow) : null;
	}
	
	public TreeMap<TimeWindow, Lesson> getLessonsByDay(DayOfWeek day) {
	    return schedule.getOrDefault(day, new TreeMap<>());
	}
	
	
	public void printSchedule() {
	    int index = 1; 
	    for (Map.Entry<DayOfWeek, TreeMap<TimeWindow, Lesson>> dayEntry : schedule.entrySet()) {
	        System.out.println(dayEntry.getKey() + ":"); 
	        TreeMap<TimeWindow, Lesson> dailySchedule = dayEntry.getValue();
	        
	        for (Map.Entry<TimeWindow, Lesson> lessonEntry : dailySchedule.entrySet()) {
	            System.out.println(index + ". " + lessonEntry.getValue()); 
	            index++; 
	        }
	    }
	}
	
	public void printSchedule(Teacher teacher) {
	    int index = 1; 
	    for (Map.Entry<DayOfWeek, TreeMap<TimeWindow, Lesson>> dayEntry : schedule.entrySet()) {
	        System.out.println(dayEntry.getKey() + ":"); 
	        TreeMap<TimeWindow, Lesson> dailySchedule = dayEntry.getValue();
	        
	        for (Map.Entry<TimeWindow, Lesson> lessonEntry : dailySchedule.entrySet()) {
	            Lesson lesson = lessonEntry.getValue();

	            if (lesson.getTeacher().equals(teacher)) {
	                System.out.println(index + ". " + lesson); 
	                index++; 
	            }
	        }
	    }
	}
	
    public Lesson selectLessonByIndex(int selectedIndex) {
        int index = 1;
        for (Map.Entry<DayOfWeek, TreeMap<TimeWindow, Lesson>> dayEntry : schedule.entrySet()) {
            TreeMap<TimeWindow, Lesson> dailySchedule = dayEntry.getValue();
            for (Map.Entry<TimeWindow, Lesson> lessonEntry : dailySchedule.entrySet()) {
                if (index == selectedIndex) {
                    System.out.println("Student selected: " + lessonEntry.getValue());
                    return lessonEntry.getValue();
                }
                index++;
            }
        }
        return null;
    }
	
}
