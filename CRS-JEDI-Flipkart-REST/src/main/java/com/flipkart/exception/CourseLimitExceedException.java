package com.flipkart.exception;
/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if a student has already registered for maximum allowed courses i.e 4
 *
 */
public class CourseLimitExceedException extends Exception {
    private Integer numberOfRegisteredCourses;
    /**
     * Constructor
     *
     * @param numberOfRegisteredCourses number of registered courses
     */
    public CourseLimitExceedException(Integer numberOfRegisteredCourses) {
        this.setNumberOfRegisteredCourses(numberOfRegisteredCourses);
    }
    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "You have already registered for " + numberOfRegisteredCourses + " courses";
    }
    public void setNumberOfRegisteredCourses(Integer numberOfRegisteredCourses) {
        this.numberOfRegisteredCourses = numberOfRegisteredCourses;
    }
}