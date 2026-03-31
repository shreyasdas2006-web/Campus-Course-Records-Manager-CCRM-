package edu.ccrm.util.exception;

/**
 * Custom exception for when a student attempts to enroll in more courses than
 * the credit limit allows.
 */
public class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}
