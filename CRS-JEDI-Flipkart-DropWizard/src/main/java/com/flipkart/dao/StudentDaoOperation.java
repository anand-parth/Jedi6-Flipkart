package com.flipkart.dao;

import com.flipkart.bean.user.Student;
import com.flipkart.business.database.JDBCConnectionInterface;
import com.flipkart.business.database.JDBCConnectionOperation;
import com.flipkart.business.user.StudentOperation;
import com.flipkart.exception.GradeCardNotGeneratedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author JEDI-06
 * Interface for Student Operations
 */
public class StudentDaoOperation implements StudentDaoInterface {
    private static final Logger LOG = Logger.getLogger(StudentDaoOperation.class);

    /**
     * Method to add student to system.
     *
     * @param student
     */
    public void addStudentToSystem(Student student) {
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        String sql = StudentOperation.studentToInsertQuery(student);
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to fetch student details from Database based on Student Id
     *
     * @param studentId
     * @return Returns a HashMap with student details
     */
    @Override
    public Map<String, String> getStudentDetails(String studentId) {
        Map<String, String> studentDetails = new HashMap<>();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT userId, studentId, username, department, semester FROM Student NATURAL JOIN User WHERE studentId=?;");
            stmt.setString(1, studentId);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                studentDetails.put("userId", result.getString(1));
                studentDetails.put("studentId", result.getString(2));
                studentDetails.put("username", result.getString(3));
                studentDetails.put("department", result.getString(4));
                studentDetails.put("semester", result.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentDetails;
    }

    /**
     * Method to view All courses available for the semester.
     *
     * @return map from course ID to view details of course Faculty and course Name.
     */
    @Override
    public Map<String, HashMap<String, String>> viewAllCourses() {
        Connection conn = getConnection();
        try {
            Map<String, HashMap<String, String>> courseDetails = new HashMap<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseCatalog;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HashMap<String, String> course = new HashMap<>();
                course.put("courseName", rs.getString(2));
                course.put("professorId", rs.getString(3));
                courseDetails.put(rs.getString(1), course);
            }
            return courseDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to fetch the studentID corresponding to the userID.
     *
     * @param userId
     * @return the studentID
     */
    @Override
    public String getStudentIdFromDatabase(String userId) {
        Connection conn = getConnection();
        String studentId = "";
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT studentId FROM Student NATURAL JOIN User WHERE userId=?;");
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                studentId = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentId;
    }

    /**
     * Method to view Grades for the current semester.
     *
     * @param studentId
     * @return grades corresponding to the student ID.
     */
    @Override
    public Map<String, String> viewGrades(String studentId) {
        Map<String, String> grades = new HashMap<>();
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Grade WHERE studentId=? AND isGenerated=1;");
            stmt.setString(1, studentId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                ++rowCount;
            }
            if (rowCount == 0) {
                throw new GradeCardNotGeneratedException();
            }
            PreparedStatement statement = conn.prepareStatement("SELECT courseId, Grade FROM Student NATURAL JOIN Grade WHERE studentId = ?");
            statement.setString(1, studentId);
            result = statement.executeQuery();
            while (result.next()) {
                grades.put(result.getString(1), result.getString(2));
            }
            return grades;
        } catch (SQLException | GradeCardNotGeneratedException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to view registered courses by the student.
     *
     * @param studentId
     * @return List of courses corresponding to studentID.
     */
    @Override
    public List<String> viewRegisteredCourses(String studentId) {
        List<String> registeredCourses = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT courseId_1, courseId_2, courseId_3, courseId_4 FROM RegisteredCourse where studentId=?;");
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                registeredCourses.add(resultSet.getString(1));
                registeredCourses.add(resultSet.getString(2));
                registeredCourses.add(resultSet.getString(3));
                registeredCourses.add(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registeredCourses;
    }

    /**
     * Method to generate a unique student ID for a new student.
     *
     * @return a string which denotes a unique student ID.
     */
    @Override
    public String generateStudentId() {
        Connection conn = getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Student;");
            Integer rowCount = 0;
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ++rowCount;
            }
            return "S0" + (rowCount + 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Method to register Courses for a student for a semester.
     *
     * @param studentId
     * @param courseChoices
     */
    @Override
    public void courseRegistration(String studentId, List<String> courseChoices) {
        Connection conn = getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("insert into choice values(?,?,?,?,?,?,?);");
            statement.setString(1, studentId);
            for (int i = 2; i <= 7; i++) {
                statement.setString(i, courseChoices.get(i - 2));
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to register Courses for a student for a semester.
     *
     * @return connection
     */
    private Connection getConnection() {
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        return conn;
    }
}
