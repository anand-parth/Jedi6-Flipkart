package com.flipkart.business.user;

import com.flipkart.bean.user.Professor;
import com.flipkart.exception.ProfessorNotAddedException;

import java.util.List;

public interface ProfessorInterface {

    /**
     * Method to add a new professor to database
     * @param professorInstance
     * @return Professor Object to be added
     */
    Professor addProfessorToSystem(Professor professorInstance);


    /**
     * Method to retrieve professorID corresponding to userId
     * @param userId
     * @return Professor ID corresponding to UserID
     */

    String getProfessorIdFromDatabase(String userId);

    /**
     * Method to get list of students enrolled in a course
     * @param s
     * @return List of enrolled students in a course
     */

    List<String> getEnrolledStudentsByCourse(String s);

    /**
     * Method to view the list of courses taken by professor with given ID
     * @param professorId
     * @return List of courses taken by professor
     */

    List<String> viewCoursesByProfessor(String professorId);

    /**
     * Method to add given professor to the given course
     * @param professorId
     * @param courseId
     * @throws ProfessorNotAddedException
     */

    void addProfessorOnCourse(String professorId, String courseId) throws ProfessorNotAddedException;

    /**
     * Method to add new grade for given student in the given course
     * @param studentId
     * @param courseId
     * @param grade
     */

    void insertGradeInDatabase(String studentId, String courseId, String grade);

    /**
     * Method to return professorId
     * @return
     */

    String generateProfessorId();
}
