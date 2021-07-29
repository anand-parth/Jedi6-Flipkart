package com.flipkart.business.user;

import com.flipkart.bean.user.Professor;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.ProfessorNotAddedException;

import java.util.List;

public class ProfessorOperation implements ProfessorInterface {

    private static final ProfessorDaoInterface professorDaoOperation = new ProfessorDaoOperation();

    @Override
    public Professor addProfessorToSystem(Professor professor) {
        return professorDaoOperation.addProfessorToSystem(professor);
    }

    @Override
    public String getProfessorIdFromDatabase(String userId) {
        return professorDaoOperation.getProfessorIdFromDatabase(userId);
    }

    @Override
    public List<String> getEnrolledStudentsByCourse(String courseId) {
        return professorDaoOperation.getEnrolledStudentsByCourse(courseId);
    }

    @Override
    public List<String> viewCoursesByProfessor(String professorId) {
        System.out.println("Courses taught by the user are: ");
        return professorDaoOperation.viewCoursesByProfessor(professorId);
    }

    @Override
    public void addProfessorOnCourse(String professorId, String courseId) throws ProfessorNotAddedException {
        professorDaoOperation.addProfessorOnCourse(professorId, courseId);
    }

    @Override
    public void insertGradeInDatabase(String studentId, String courseId, String grade) {
        professorDaoOperation.insertGradeInDatabase(studentId, courseId, grade);
    }

    @Override
    public String generateProfessorId() {
        return professorDaoOperation.generateProfessorId();
    }
}
