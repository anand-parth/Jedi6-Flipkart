package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if course is  not assigned to a professor
 *
 */
public class CourseNotAssignedToProfessorException extends Exception{
	private String courseId;
	private String professorId;
	/**
	 * Constructor
	 * @param courseId, professorId
	 */
	public CourseNotAssignedToProfessorException(String courseId, String professorId) {
		this.courseId = courseId;
		this.professorId = professorId;
	}
	
	/**
	 * Get courseId
	 * @return courseId
	 */
	public String getCourseId() {
		return courseId;
	}
	/**
	 * get Professor id
	 * @return professor id
	 */
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * set professor id
	 * @param professorId
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * set courseId
	 * @param courseId
	 */
	public void setCourseCode(String courseId) {
		this.courseId = courseId;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "courseId: " + courseId + " OR professorId: " + professorId + " does not exist!";
	}
}
