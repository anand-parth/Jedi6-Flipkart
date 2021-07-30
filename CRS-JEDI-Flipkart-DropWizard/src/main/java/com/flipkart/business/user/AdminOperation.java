package com.flipkart.business.user;

import com.flipkart.bean.academics.Course;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotFoundException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 */

public class AdminOperation implements AdminInterface {

    private static final Logger LOG = Logger.getLogger(AdminOperation.class);
    public static final AdminDaoInterface adminDaoOperation = new AdminDaoOperation();

    /**
     * Method to approve Student for login
     *
     * @param studentId: student ID to approve
     */
    @Override
    public void approveStudent(String studentId) {
        adminDaoOperation.approveStudent(studentId);
    }

    /**
     * Method to delete User from database
     *
     * @param deleteUserId: User ID to delete
     */
    public void deleteUser(String deleteUserId) {
        adminDaoOperation.deleteUser(deleteUserId);
        LOG.info("User Record for " + deleteUserId + " deleted successfully!!!");
    }

    /**
     * Method to view pending students awaiting approval for login
     *
     * @return List of pending student approvals
     */
    public List<String> viewPendingApprovals() {
        return adminDaoOperation.viewPendingApprovals();
    }


    /**
     * Method to view Course details in Catalog
     *
     * @param courseId
     * @return Map of course detail type and corresponding values
     */
    @Override
    public Map<String, String> fetchCourseDetails(String courseId) {
        return adminDaoOperation.fetchCourseDetails(courseId);
    }

    /**
     * Method to update course details in database
     *
     * @param courseId
     * @param courseName
     * @param courseFaculty
     */
    @Override
    public void updateCourseDetails(String courseId, String courseName, String courseFaculty) {
        System.out.println(courseId + " " + courseName + " " + courseFaculty);
        adminDaoOperation.updateCourseDetails(courseId, courseName, courseFaculty);
    }

    /**
     * Method to add a new course in catalog
     *
     * @param course
     * @throws CourseNotFoundException
     */
    @Override
    public void addCourse(Course course) {
        adminDaoOperation.addCourse(course);
    }

    /**
     * Method to generate grade card of student after professor uploads grades
     *
     * @param studentId
     */
    @Override
    public void generateGradeCard(String studentId) {
        adminDaoOperation.generateGradeCard(studentId);
    }
}
