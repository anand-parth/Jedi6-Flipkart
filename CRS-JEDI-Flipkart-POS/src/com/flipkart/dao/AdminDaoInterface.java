package com.flipkart.dao;

import com.flipkart.bean.academics.Course;

import java.util.List;
import java.util.Map;

/**
 *
 * @author JEDI-06
 * Interface for Admin Operations
 *
 */
public interface AdminDaoInterface {

    /**
     * Method to view which users have not been approved by the Admin.
     * @return List of users which are pending for Admin approval.
     */
    List<String> viewPendingApprovals();

    /**
     * Method to approve student corresponding to studentID.
     * @param studentId
     */
    void approveStudent(String studentId);

    /**
     * Method to fetch Course details corresponding to the respective courseID.
     * @param courseId
     * @return map from the courseId to the corresponding course Details.
     */
    Map<String, String> fetchCourseDetails(String courseId);

    /**
     * Method to update Course Details using the course ID, name and course Faculty.
     * @param courseId
     * @param courseName
     * @param courseFaculty
     */
    void updateCourseDetails(String courseId, String courseName, String courseFaculty);

    /**
     * Method to add a Course to the course catalogue.
     * @param course
     */
    void addCourse(Course course);

    /**
     * Method to generate grade Card for a student using the student ID.
     * @param studentId
     */
    void generateGradeCard(String studentId);

    /**
     * Method to delete a user using the user ID.
     * @param deleteUserId
     */
    void deleteUser(String deleteUserId);
}
