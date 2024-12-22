package courses;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeWindow implements Comparable<TimeWindow> {
	private LocalTime startTime;
	private LocalTime endTime;

 
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
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeWindow that = (TimeWindow) o;
        return startTime.equals(that.startTime) && endTime.equals(that.endTime);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    public String toString() {
    	return "(" + this.startTime + "-" + this.endTime + ")";
    }

}
