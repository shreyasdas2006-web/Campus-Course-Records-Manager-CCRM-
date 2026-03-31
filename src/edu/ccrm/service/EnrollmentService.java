package edu.ccrm.service;

import edu.ccrm.config.AppConfig;
import edu.ccrm.model.Course;
import edu.ccrm.model.Enrollment;
import edu.ccrm.model.Grade;
import edu.ccrm.model.Student;
import edu.ccrm.util.exception.DuplicateEnrollmentException;
import edu.ccrm.util.exception.MaxCreditLimitExceededException;

import java.util.List;
import java.util.ArrayList;

/**
 * Contains business logic for student enrollment and grading.
 */
public class EnrollmentService {

    private AppConfig appConfig = AppConfig.getInstance();
    private static final int MAX_CREDITS = 21;

    public void enrollStudent(Student student, Course course)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        for (Enrollment enrollment : appConfig.getAllEnrollments()) {
            if (enrollment.getStudent() == student && enrollment.getCourse() == course) {
                throw new DuplicateEnrollmentException(student.getName() + " is already enrolled in that course.");
            }
        }
        int currentCredits = 0;
        for (Enrollment enrollment : appConfig.getAllEnrollments()) {
            if (enrollment.getStudent() == student) {
                currentCredits += enrollment.getCourse().getCredits();
            }
        }
        if (currentCredits + course.getCredits() > MAX_CREDITS) {
            throw new MaxCreditLimitExceededException(
                    "Adding this course would exceed the credit limit of " + MAX_CREDITS);
        }
        Enrollment newEnrollment = new Enrollment(student, course);
        appConfig.getAllEnrollments().add(newEnrollment);
        student.addCourse(course);
        System.out.println("Success! " + student.getName() + " is now enrolled in " + course.getTitle());
    }

    public void recordMarks(Student student, Course course, int marks) {
        for (Enrollment enrollment : appConfig.getAllEnrollments()) {
            if (enrollment.getStudent() == student && enrollment.getCourse() == course) {
                Grade grade = Grade.fromMarks(marks);
                enrollment.setGrade(grade);
                System.out.println("Marks recorded. " + student.getName() + " gets grade " + grade.name() + " for "
                        + course.getTitle());
                return;
            }
        }
        System.out.println("Error: Could not find a matching enrollment record to assign marks.");
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        List<Enrollment> studentEnrollments = new ArrayList<>();
        for (Enrollment enrollment : appConfig.getAllEnrollments()) {
            if (enrollment.getStudent() == student) {
                studentEnrollments.add(enrollment);
            }
        }
        return studentEnrollments;
    }

    public double computeGpa(Student student) {
        List<Enrollment> studentEnrollments = getEnrollmentsForStudent(student);
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Enrollment enrollment : studentEnrollments) {
            if (enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                // This line will now compile correctly
                totalPoints += enrollment.getGrade().getGradePoints() * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return totalPoints / totalCredits;
    }

    public void printAllEnrollments() {
        System.out.println("\n--- All Enrollments ---");
        List<Enrollment> enrollments = appConfig.getAllEnrollments();
        if (enrollments.isEmpty()) {
            System.out.println("No students are enrolled in any courses yet.");
        } else {
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        }
        System.out.println("-----------------------\n");
    }
}