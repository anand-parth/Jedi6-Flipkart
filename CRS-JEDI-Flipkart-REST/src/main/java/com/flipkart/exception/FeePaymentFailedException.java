package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check fee payment failure.
 *
 */
public class FeePaymentFailedException extends Exception{
    private String studentId;

    public FeePaymentFailedException(String studentId) {
        this.studentId = studentId;
    }

    public String getMessage() {
        return "Fee Payment Failed. Please Try again.";
    }
}
