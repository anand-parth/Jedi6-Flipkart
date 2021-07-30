package com.flipkart.exception;

/**
 * @author JEDI-6-Flipkart-G1
 * Exception to check if student is not registered
 */
public class StudentNotRegisteredException extends Exception {
    private String studentName;

    /**
     * Constructor
     *
     * @param studentName
     */

    public StudentNotRegisteredException(String studentName) {
        this.studentName = studentName;
    }

    /**
     * getter function for studentName
     *
     * @return
     */
    public String getStudentName() {
        return studentName;
    }

}