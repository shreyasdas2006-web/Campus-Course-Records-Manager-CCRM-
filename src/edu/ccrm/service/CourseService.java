package edu.ccrm.service;

import edu.ccrm.config.AppConfig;
import edu.ccrm.model.Course;
import edu.ccrm.model.Instructor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains business logic for course management.
 */
public class CourseService {

    private AppConfig appConfig = AppConfig.getInstance();

    public void addCourse(Course course) {
        appConfig.getAllCourses().add(course);
        System.out.println("Course added: " + course.getTitle());
    }

    public Course findCourseByCode(String code) {
        for (Course course : appConfig.getAllCourses()) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public List<Course> findCoursesByInstructor(Instructor instructor) {
        return appConfig.getAllCourses().stream()
                .filter(course -> course.getInstructor() != null)
                .filter(course -> course.getInstructor().equals(instructor))
                .collect(Collectors.toList());
    }

    public void printAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = appConfig.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
        System.out.println("-------------------\n");
    }
}
