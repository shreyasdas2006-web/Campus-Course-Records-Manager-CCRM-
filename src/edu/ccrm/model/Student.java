package edu.ccrm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student, inheriting from Person.
 */
public class Student extends Person {

    private String regNo;
    private List<Course> enrolledCourses;

    public Student(int id, String name, String email, String regNo) {
        super(id, name, email);
        this.regNo = regNo;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    @Override
    public String getDetails() {
        return "Student: " + name + ", Reg No: " + regNo;
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
