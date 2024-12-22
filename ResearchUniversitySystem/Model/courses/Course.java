package courses;

import java.util.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

import enums.CourseType;
import enums.LessonType;
import enums.Semester;
import users.students.Student;
import users.employees.Teacher;


public class Course implements Serializable {
	
	private String courseID;
	private String courseName;
    private String description = "";
    private EnumMap<LessonType, Integer> credits = new EnumMap<>(LessonType.class);   // its like (2/0/1 - 2 lectures, 0 labs, 1 practice)
    private HashSet<Course> prerequesites = new HashSet<>();
    
    private HashMap<Integer, Lesson> courseLessons = new HashMap<>();
    private Schedule courseSchedule = new Schedule();
    
    private Vector<Teacher> courseTeachers = new Vector<>();
    
    // Current
    private Semester semester = Semester.FALL;
    private CourseType courseType = CourseType.MAJOR;
    
    private GradeReport gradeReport;
    private HashMap<Student,Mark> gradeBook;

    private int lessonID;
    
    {
    	lessonID = 0;
    	gradeBook = new HashMap<>();
    	gradeReport = new GradeReport(this);
    }
    
    public Course(String courseID, String courseName) {
    	this.courseID = courseID;
    	this.courseName = courseName;
    }
    
    public Course(String courseID, String courseName, EnumMap<LessonType, Integer> credits) {
    	this(courseID, courseName);
    	this.credits = credits;
    }
   
    public Course(String courseID, String courseName, HashSet<Course> prerequesites, EnumMap<LessonType, Integer> credits) {
    	this(courseID, courseName, credits);
    	this.prerequesites = prerequesites;
    }
 
    public boolean checkPrerequesites(Student student) {
        for (Course c : prerequesites) {
        	if (!student.getCompletedCourses().contains(c)) {
        		return false;
        	}
        }
        return true;
    }
    
    
    public void addMarkForStudent(Student student, double mark, int attestation) {
    	if (attestation == 1) {
    		gradeBook.get(student).addFirstAttestationMark(mark);        
    	}
    	else if (attestation == 2) {
    		gradeBook.get(student).addSecondAttestationMark(mark);  
    	}
    }

    public void addTeacherToCourse(Teacher teacher) {
    	courseTeachers.add(teacher);
    }
     
    public void addCourseLesson(Lesson lesson) {
    	lesson.setID(generateLessonID());
    	courseLessons.put(lesson.getID(), lesson);
    }
    
    public void fillLessons(int lessonRoom, LocalDate lessonFromDate, DayOfWeek dayOfWeek, TimeWindow lessonTime, 
    		LessonType lessonType, Teacher teacher) {
    	Lesson newLesson = null;
        for (int i = 0; i < 15; i++) {
            LocalDate newDate = lessonFromDate.plusWeeks(i);
            newLesson = new Lesson(this, lessonRoom, newDate, dayOfWeek, lessonTime, lessonType, teacher);

            addCourseLesson(newLesson);
        }
        teacher.getSchedule().addLesson(newLesson); 
        courseSchedule.addLesson(newLesson);
    }
    
    public void removeLessonFromCourseByID(Integer lessonID) {
    	Lesson lesson = courseLessons.remove(lessonID);
    	if (lesson != null) {
            courseSchedule.removeLesson(lesson); 
        }
    }
    
    
    // Views
    public void viewLessonInSchedule() {
    	courseSchedule.printSchedule();
    }
    
    public void viewLessonInSchedule(Teacher teacher) {
    	courseSchedule.printSchedule(teacher);
    }
    
	public void viewLessons() {
	    courseLessons.values()
	                 .forEach(System.out::println);
	}

	public void viewLessons(Teacher teacher) {
	    courseLessons.values().stream()
	                 .filter(lesson -> lesson.getTeacher().equals(teacher))
	                 .forEach(System.out::println);
	}

	public void viewLessons(Date date) {
	    courseLessons.values().stream()
	                 .filter(lesson -> lesson.getDate().equals(date))
	                 .forEach(System.out::println);
	}
	
	// Getters
    public String getID() {
    	return courseID;
    }
    
    public Lesson getLessonByID(Integer lessonID) {
    	return courseLessons.get(lessonID);
    }
    
	public HashMap<Integer, Lesson> getCourseLessons() {
    	return courseLessons;
    }
	
	public Schedule getCourseSchedule() {
		return courseSchedule;
	}
    
    public EnumMap<LessonType, Integer> getCredits() {
    	return credits;
    }
    
    public Vector<Teacher> getCourseTeachers() {
    	return courseTeachers;
    }
    
    public int getOverallCredits() {
    	if (credits.isEmpty()) return 0;
    	return credits.values().stream()
        .mapToInt(Integer::intValue)
        .sum();
    }
    
    public Set<Student> getStudents() {
    	return gradeBook.keySet();
	}

    public HashMap<Student,Mark> getGradeBook() {
        return gradeBook;
    }

    public GradeReport getGradeReport() {
        return gradeReport;
    }
    
    
    //Setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrerequesites(HashSet<Course> prerequesites) {
        this.prerequesites = prerequesites;
    }

    public void setCourseLessons(HashMap<Integer, Lesson> courseLessons) {
        this.courseLessons = courseLessons;
    }

    public void setCourseTeachers(Vector<Teacher> courseTeachers) {
        this.courseTeachers = courseTeachers;
    }

    public void setCredits(LessonType type, int creditValue) {
        credits.put(type, creditValue);
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setGradeBook(HashMap<Student, Mark> gradeBook) {
        this.gradeBook = gradeBook;
    }

	public String getCourseName() {
		return courseName;
	}
	
	// ID generation for Lessons in this course
    private Integer generateLessonID() {
		return lessonID++;
	}
    
    public String toString() {
        return "Course:\n" +
               "  ID: " + courseID + "\n" +
               "  Name: " + courseName + "\n" +
               "  Description: " + description + "\n" +
               "  Credits: " + getOverallCredits() + "\n" +
               "  Semester: " + semester + "\n" +
               "  Type: " + courseType;
    }


}
