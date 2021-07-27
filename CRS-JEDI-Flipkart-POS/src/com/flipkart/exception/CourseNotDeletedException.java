/**
 * 
 */
package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if course is deleted from catalog
 *
 */
public class CourseNotDeletedException extends Exception{
private String courseId;
/**
 * Constructor
 * @param courseId
 */
	public CourseNotDeletedException(String courseId)
	{	
		this.courseId = courseId;
	}

	/**
	 * Getter function for course code
	 * @return
	 */
	public String getCourseId()
	{
		return courseId;
	}
	
	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() 
	{
		return "Course with courseCode: " + courseId + " can not be deleted.";
	}
}