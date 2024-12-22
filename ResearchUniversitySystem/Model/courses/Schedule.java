package courses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
	
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
	
	public boolean removeLesson(Lesson lesson) {
	    for (Map.Entry<DayOfWeek, TreeMap<TimeWindow, Lesson>> entry : schedule.entrySet()) {
	        DayOfWeek day = entry.getKey();
	        TreeMap<TimeWindow, Lesson> daySchedule = entry.getValue();

	        TimeWindow timeWindowToRemove = null;
	        for (Map.Entry<TimeWindow, Lesson> lessonEntry : daySchedule.entrySet()) {
	            if (lessonEntry.getValue().equals(lesson)) {
	                timeWindowToRemove = lessonEntry.getKey();
	                break;
	            }
	        }

	        if (timeWindowToRemove != null) {
	            daySchedule.remove(timeWindowToRemove);
	            if (daySchedule.isEmpty()) {
	                schedule.remove(day);
	            }
	            return true; 
	        }
	    }
	    return false; 
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
