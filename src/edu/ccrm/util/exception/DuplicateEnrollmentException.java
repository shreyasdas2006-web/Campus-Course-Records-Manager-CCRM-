package edu.ccrm.util.exception;

/**
 * Custom exception for when a student attempts to enroll in the same course
 * twice.
 */
public class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}
