package com.flipkart.dao;

import com.flipkart.bean.academics.Course;
import com.flipkart.business.database.JDBCConnectionInterface;
import com.flipkart.business.database.JDBCConnectionOperation;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoOperation implements AdminDaoInterface {

    private static final Logger LOG = Logger.getLogger(AdminDaoOperation.class);

    /**
     * Method to fetch Students yet to be approved by the Admin
     * @return List of Student Ids with pending approval
     */
    @Override
    public List<String> viewPendingApprovals() {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT studentId FROM Student WHERE isApproved = 0;");
            ResultSet result = stmt.executeQuery();
            List<String> pendingList = new ArrayList<String>();
            while (result.next()) {
                pendingList.add(result.getString(1));
            }
            result.close();
            return pendingList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void approveStudent(String studentId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Student WHERE studentId = ?;");
            stmt.setString(1, studentId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                ++rowCount;
            }
            if (rowCount == 0) {
                throw new StudentNotFoundForApprovalException(studentId);
            }
            stmt = conn.prepareStatement("UPDATE Student SET isApproved = 1 where studentId = ?;");
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            LOG.info("Student " + studentId + " Approved!! ");
            result.close();
        } catch (SQLException | StudentNotFoundForApprovalException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> fetchCourseDetails(String courseId) {
        Connection conn = getConnection();
        try {
            Map<String, String> courseDetails = new HashMap<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseCatalog WHERE courseId=?;");
            stmt.setString(1, courseId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                ++rowCount;
            }
            if (rowCount == 0) {
                throw new CourseNotFoundException(courseId);
            }
            stmt = conn.prepareStatement("SELECT courseId, courseName, professorId FROM CourseCatalog WHERE courseId=?;");
            stmt.setString(1, courseId);
            result = stmt.executeQuery();
            while (result.next()) {
                courseDetails.put("courseId", result.getString(1));
                courseDetails.put("courseName", result.getString(2));
                courseDetails.put("professorId", result.getString(3));
            }
            result.close();
            return courseDetails;
        } catch (SQLException | CourseNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateCourseDetails(String courseId, String courseName, String professorId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CourseCatalog WHERE courseId=?;");
            stmt.setString(1, courseId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while(result.next()){
                ++rowCount;
            }
            if (rowCount == 0) {
                throw new CourseNotFoundException(courseId);
            }
            stmt = conn.prepareStatement("UPDATE CourseCatalog SET professorId=? , courseName=? WHERE courseId=?;");
            stmt.setString(1, professorId);
            stmt.setString(2, courseName);
            stmt.setString(3, courseId);
            stmt.executeUpdate();
            LOG.info("Course " + courseId + " Updated!! ");
            result.close();
        } catch (SQLException | CourseNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCourse(Course course) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO CourseCatalog Values(?,?,NULL);");
            stmt.setString(1,course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.executeUpdate();
            LOG.info("Course Added!!");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateGradeCard(String studentId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Grade SET isGenerated=1 where studentId=?");
            stmt.setString(1, studentId);
            stmt.executeUpdate();
            LOG.info("GradeCard generated successfully!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(String userId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT userRole FROM User WHERE userId=?;");
            stmt.setString(1,userId);
            ResultSet result = stmt.executeQuery();
            // if user not present
            // if user is a professor
            Integer userRole = null;
            Integer rowCount = 0;
            while(result.next()){
                 userRole = result.getInt(1);
                 ++rowCount;
            }
            if(rowCount.equals(0)){
                throw new UserNotFoundException(userId);
            }
            if(userRole.equals(1)){
                stmt = conn.prepareStatement("SELECT professorId FROM Professor WHERE userId=?;");
                stmt.setString(1,userId);
                result = stmt.executeQuery();
                result.next();
                String professorId = result.getString(1);
                stmt = conn.prepareStatement("UPDATE CourseCatalog SET professorId=NULL WHERE profesorId=?;");
                stmt.setString(1, professorId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Credentials WHERE userId=?;");
                stmt.setString(1,userId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Professor WHERE userId=?;");
                stmt.setString(1,userId);
                stmt.executeUpdate();
            }
            // if user is a student
            else if(userRole.equals(2)){
                stmt = conn.prepareStatement("SELECT studentId FROM Student WHERE userId=?;");
                stmt.setString(1,userId);
                result = stmt.executeQuery();
                result.next();
                String studentId = result.getString(1);
                stmt = conn.prepareStatement("DELETE FROM RegisteredCourse WHERE studentId=?;");
                stmt.setString(1,studentId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Choice WHERE studentId=?;");
                stmt.setString(1,studentId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Grade WHERE studentId=?;");
                stmt.setString(1,studentId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Credentials WHERE userId=?;");
                stmt.setString(1,userId);
                stmt.executeUpdate();
                stmt = conn.prepareStatement("DELETE FROM Student WHERE userId=?;");
                stmt.setString(1,userId);
                stmt.executeUpdate();
            }
            stmt = conn.prepareStatement("DELETE FROM User WHERE userId=?;");
            stmt.setString(1,userId);
            stmt.executeUpdate();
            result.close();
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        return conn;
    }
}
