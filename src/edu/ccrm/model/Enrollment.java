package edu.ccrm.model;

import java.time.LocalDate;

/**
 * Represents the link between a Student and a Course they are enrolled in.
 */
public class Enrollment {

    private Student student;
    private Course course;
    private Grade grade;
    private LocalDate enrollmentDate;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
        this.grade = null; // No grade assigned upon enrollment.
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        String gradeStr = (grade == null) ? "Not Graded Yet" : grade.name();
        return student.getName() + " is enrolled in " + course.getTitle() + " | Grade: " + gradeStr;
    }
}
