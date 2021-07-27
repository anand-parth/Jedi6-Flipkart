package com.flipkart.dao;

import com.flipkart.bean.user.Professor;

import java.util.List;

/**
 *
 * @author JEDI-06
 * Interface for Professor Operations
 *
 */
public interface ProfessorDaoInterface {

    /**
     * Method to add Professor to the database system
     * @param professor
     * @return Professor object which has been added
     */
    Professor addProfessorToSystem(Professor professor);

    /**
     * Method to obtain professorId from database
     * @param userId
     * @return string indicating professorID
     */
    String getProfessorIdFromDatabase(String userId);

    /**
     * Method to view all courses taught by the professor for corresponding ProfessorId
     * @param professorID
     * @return List of courses to view courses for each Professor
     */
    List<String> viewCoursesByProfessor(String professorId);


    /**
     * Method to view all courses corresponding to each course ID.
     * @param courseId
     * @return List of courseIDs to view enrolled students for a course
     */
    List<String> getEnrolledStudentsByCourse(String courseId);

    /**
     * Method to insert a grade for corresponding student and course.
     * @param studentId
     * @param courseId
     * @param grade
     */
    void insertGradeInDatabase(String studentId, String courseId, String grade);

    /**
     * Method to add a Professor corresponding to a course ID.
     * @param professorId
     * @param courseId
     */
    void addProfessorOnCourse(String professorId, String courseId);

    /**
     * Method to generate professor ID for a unique ID for each added professor.
     * @return unique Professor ID.
     */
    String generateProfessorId();
}
