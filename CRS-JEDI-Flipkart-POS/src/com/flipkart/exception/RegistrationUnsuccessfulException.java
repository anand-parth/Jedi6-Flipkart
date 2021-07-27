package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if registration is unsuccessful
 *
 */
public class RegistrationUnsuccessfulException extends Exception{
    private String studentId;

    public RegistrationUnsuccessfulException(String studentId) {
        this.studentId = studentId;
    }

    public String getMessage()
    {
        return "Registration Unsuccessful. Please Try again.";
    }
}
