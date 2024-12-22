package courses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
        LocalDate date = lesson.getDate();
        TimeWindow time = lesson.getLessonTime();
        return;
    }
	
	
}
