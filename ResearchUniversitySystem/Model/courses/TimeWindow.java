package courses;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeWindow implements Comparable<TimeWindow> {
	private LocalTime startTime;
	private LocalTime endTime;

    /**
     * Default constructor
     */
    public TimeWindow(LocalTime startTime, LocalTime endTime) {
    	if (startTime.isAfter(endTime)) {
    		throw new IllegalArgumentException("Start time must be before end time!"); // ensuring consistency in time
    	}
    	this.startTime = startTime.truncatedTo(ChronoUnit.MINUTES);
    	this.endTime = endTime.truncatedTo(ChronoUnit.MINUTES); // ensuring we get only hours and minutes
    }
    
    public LocalTime getStartTime() {
    	return this.startTime;
    }
    
    public LocalTime getEndTime() {
    	return this.endTime;
    }
    
    public int compareTo(TimeWindow other) {
        return this.startTime.compareTo(other.startTime);
    }

    public String toString() {
    	return "(" + this.startTime + "-" + this.endTime + ")";
    }

}
