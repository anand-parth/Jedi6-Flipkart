package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if course is already registered by student
 *
 */
public class CourseAlreadyRegisteredException extends Exception{
	
	private String courseId;
	
	/**
	 * Constructor
	 * @param courseId
	 */
	public CourseAlreadyRegisteredException(String courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Getter method
	 * @return courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	
	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "You have already registered for " + courseId;
	}

}