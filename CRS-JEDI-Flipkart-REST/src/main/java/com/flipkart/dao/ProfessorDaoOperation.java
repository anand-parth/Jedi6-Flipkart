package com.flipkart.dao;

import com.flipkart.bean.user.Professor;
import com.flipkart.business.database.JDBCConnectionInterface;
import com.flipkart.business.database.JDBCConnectionOperation;
import com.flipkart.exception.ProfessorNotAddedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDaoOperation implements ProfessorDaoInterface {

    private static final Logger LOG = Logger.getLogger(ProfessorDaoOperation.class);

    @Override
    public Professor addProfessorToSystem(Professor professor) {
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        String sql = professorToInsertQuery(professor);
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor;
    }

    @Override
    public String getProfessorIdFromDatabase(String userId) {
        Connection conn = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT professorId FROM Professor WHERE userId=? ;");
            stmt.setString(1, userId);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                return result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> viewCoursesByProfessor(String professorId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT courseId FROM CourseCatalog WHERE professorId=?;");
            stmt.setString(1, professorId);
            ResultSet result = stmt.executeQuery();
            List<String> coursesList = new ArrayList<String>();
            while (result.next()) {
                coursesList.add(result.getString(1));
            }
            return coursesList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getEnrolledStudentsByCourse(String courseId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT studentId FROM RegisteredCourse WHERE courseId_1 = ? OR courseId_2 = ? OR courseId_3 = ? OR courseId_4 = ?;");
            stmt.setString(1, courseId);
            stmt.setString(2, courseId);
            stmt.setString(3, courseId);
            stmt.setString(4, courseId);
            ResultSet rs = stmt.executeQuery();
            List<String> list = new ArrayList<String>();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertGradeInDatabase(String studentId, String courseId, String grade) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Grade SET grade=? WHERE courseId=? and studentId=?;");
            stmt.setString(1, grade);
            stmt.setString(2, courseId);
            stmt.setString(3, studentId);
            stmt.executeUpdate();
            LOG.info("Grade Added!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProfessorOnCourse(String professorId, String courseId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseCatalog WHERE courseId=? AND professorId IS NOT NULL;");
            stmt.setString(1, courseId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                ++rowCount;
            }
            if (rowCount > 0) {
                throw new ProfessorNotAddedException(professorId);
            }
            stmt = conn.prepareStatement("UPDATE CourseCatalog SET professorId=? WHERE courseId=?;");
            stmt.setString(1, professorId);
            stmt.setString(2, courseId);
            stmt.executeUpdate();
            LOG.info("Professor: " + professorId + " added on course: " + courseId);
        } catch (SQLException | ProfessorNotAddedException e) {
            e.printStackTrace();
        }
    }

    public static String professorToInsertQuery(Professor professor) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Professor values('");
        sql.append(professor.getloginId()).append("','");
        sql.append(professor.getProfessorId()).append("','");
        sql.append(professor.getDepartment()).append("','");
        sql.append(professor.getDesignation().toString()).append("')");
        return sql.toString();
    }

    @Override
    public String generateProfessorId() {
        Connection conn = getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Professor;");
            Integer rowCount = 0;
            ResultSet result = statement.executeQuery();
            while(result.next()){
                ++rowCount;
            }
            return "P0"+(rowCount+1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private Connection getConnection() {
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        return conn;
    }
}
