/**
 * 
 */
package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if course is already present in catalo
 *
 */
public class CourseFoundException extends Exception{
	private String courseCode;
	
	/***
	 * Constructor
	 * @param courseCode
	 */
	public CourseFoundException(String courseCode) {
		this.courseCode = courseCode;
	}
	

	/**
	 * Getter method
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " already present in catalog.";
	}
}
