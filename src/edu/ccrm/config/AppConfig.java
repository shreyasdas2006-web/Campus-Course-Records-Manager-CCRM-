package edu.ccrm.config;

import edu.ccrm.model.Course;
import edu.ccrm.model.Enrollment;
import edu.ccrm.model.Instructor;
import edu.ccrm.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * A Singleton class to hold the application's in-memory data store.
 */
public class AppConfig {

    private static AppConfig instance;

    private List<Student> allStudents;
    private List<Instructor> allInstructors;
    private List<Course> allCourses;
    private List<Enrollment> allEnrollments;

    // Private constructor to prevent direct instantiation.
    private AppConfig() {
        allStudents = new ArrayList<>();
        allInstructors = new ArrayList<>();
        allCourses = new ArrayList<>();
        allEnrollments = new ArrayList<>();
    }

    /**
     * Provides the single instance of the AppConfig class.
     * 
     * @return The singleton instance.
     */
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    // Getters for the data lists
    public List<Student> getAllStudents() {
        return allStudents;
    }

    public List<Instructor> getAllInstructors() {
        return allInstructors;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public List<Enrollment> getAllEnrollments() {
        return allEnrollments;
    }
}
