package com.flipkart.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JEDI-06
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {

    /**
     * Method to get the Student Details for a student ID.
     * @param studentId
     * @return map from student ID to the corresponding student ID.
     */
    Map<String, String> getStudentDetails(String studentId);

    /**
     * Method to view All courses available for the semester.
     * @return map from course ID to view details of course Faculty and course Name.
     */
    Map<String, HashMap<String, String>> viewAllCourses();

    /**
     * Method to fetch the studentID corresponding to the userID.
     * @param userId
     * @return the studentID
     */
    String getStudentIdFromDatabase(String userId);

    /**
     * Method to view Grades for the current semester.
     * @param studentId
     * @return grades corresponding to the student ID.
     */
    Map<String,String> viewGrades(String studentId);

    /**
     * Method to view registered courses by the student.
     * @param studentId
     * @return List of courses corresponding to studentID.
     */
    List<String> viewRegisteredCourses(String studentId);

    /**
     * Method to generate a unique student ID for a new student.
     * @return a string which denotes a unique student ID.
     */
    String generateStudentId();

    /**
     * Method to register Courses for a student for a semester.
     * @param studentId
     * @param courseChoices
     */
    void courseRegistration(String studentId, List<String> courseChoices);
}
