package courses;

import java.util.Date;

import courses.TimeWindow;
import enums.LessonType;
import users.employees.Teacher;
import users.students.Student;


/**
 * 
 */
public class Lesson {

    /**
     * Default constructor
     */
    public Lesson() {
    }

    /**
     * 
     */
    public LessonType type;

    /**
     * 
     */
    private TimeWindow lessonTime;

    /**
     * 
     */
    private int lessonRoom;

    /**
     * 
     */
    private LessonType lessonType;

    /**
     * 
     */
    public Teacher teacher;

    /**
     * 
     */
    public Date lessonDate;

    /**
     * @param student 
     * @param status 
     * @return
     */
    public void markAttendance(Student student, boolean status) {
        // TODO implement here
        return;
    }

}