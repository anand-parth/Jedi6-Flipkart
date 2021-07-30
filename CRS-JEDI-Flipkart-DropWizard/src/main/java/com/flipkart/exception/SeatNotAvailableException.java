package com.flipkart.exception;

/**
 * @author JEDI-6-Flipkart-G1
 * Exception to check if seats are available for course registration
 */
public class SeatNotAvailableException extends Exception {

    private String courseId;

    /**
     * Constructor
     *
     * @param courseId
     */
    public SeatNotAvailableException(String courseId) {
        this.courseId = courseId;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Seats are not available in : " + courseId;
    }


}