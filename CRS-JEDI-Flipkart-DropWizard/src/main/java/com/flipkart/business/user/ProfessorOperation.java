package com.flipkart.business.user;

import com.flipkart.bean.user.Professor;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.ProfessorNotAddedException;

import java.util.List;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 */

public class ProfessorOperation implements ProfessorInterface {

    private static final ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();

    /**
     * Method to add a new professor to database
     *
     * @param professor
     * @return Professor Object to be added
     */
    @Override
    public Professor addProfessorToSystem(Professor professor) {
        return professorDaoOperation.addProfessorToSystem(professor);
    }

    /**
     * Method to retrieve professorID corresponding to userId
     *
     * @param userId
     * @return Professor ID corresponding to UserID
     */
    @Override
    public String getProfessorIdFromDatabase(String userId) {
        return professorDaoOperation.getProfessorIdFromDatabase(userId);
    }

    /**
     * Method to get list of students enrolled in a course
     *
     * @param courseId
     * @return List of enrolled students in a course
     */
    @Override
    public List<String> getEnrolledStudentsByCourse(String courseId) {
        return professorDaoOperation.getEnrolledStudentsByCourse(courseId);
    }

    /**
     * Method to view the list of courses taken by professor with given ID
     *
     * @param professorId
     * @return List of courses taken by professor
     */
    @Override
    public List<String> viewCoursesByProfessor(String professorId) {
        System.out.println("Courses taught by the user are: ");
        return professorDaoOperation.viewCoursesByProfessor(professorId);
    }

    /**
     * Method to add given professor to the given course
     *
     * @param professorId
     * @param courseId
     * @throws ProfessorNotAddedException
     */
    @Override
    public void addProfessorOnCourse(String professorId, String courseId) throws ProfessorNotAddedException {
        professorDaoOperation.addProfessorOnCourse(professorId, courseId);
    }

    /**
     * Method to add new grade for given student in the given course
     *
     * @param studentId
     * @param courseId
     * @param grade
     */
    @Override
    public void insertGradeInDatabase(String studentId, String courseId, String grade) {
        professorDaoOperation.insertGradeInDatabase(studentId, courseId, grade);
    }

    /**
     * Method to return professorId
     *
     * @return
     */
    @Override
    public String generateProfessorId() {
        return professorDaoOperation.generateProfessorId();
    }
}
