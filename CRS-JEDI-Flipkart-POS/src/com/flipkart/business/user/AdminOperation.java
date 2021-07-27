package com.flipkart.business.user;

import com.flipkart.bean.academics.Course;
import com.flipkart.bean.user.Student;
import com.flipkart.bean.user.User;
import com.flipkart.crs.CRSApplication;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exception.CourseFoundException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class AdminOperation implements AdminInterface{

    private static final Logger LOG = Logger.getLogger(AdminOperation.class);
    public static final AdminDaoInterface adminDaoOperation = new AdminDaoOperation();

    @Override
    public void approveStudent(String studentId) {
        adminDaoOperation.approveStudent(studentId);
    }

    public void deleteUser(String deleteUserId){
        adminDaoOperation.deleteUser(deleteUserId);
        LOG.info("User Record for " + deleteUserId + " deleted successfully!!!");
    }

    public List<String> viewPendingApprovals(){
        return adminDaoOperation.viewPendingApprovals();
    }

    @Override
    public Map<String, String> fetchCourseDetails(String courseId) {
        return adminDaoOperation.fetchCourseDetails(courseId);
    }

    @Override
    public void updateCourseDetails(String courseId, String courseName, String courseFaculty) {
        System.out.println(courseId +" " + courseName + " "+courseFaculty);
        adminDaoOperation.updateCourseDetails(courseId, courseName, courseFaculty);
    }

    @Override
    public void addCourse(Course course) throws CourseFoundException {
        adminDaoOperation.addCourse(course);
    }

    @Override
    public void generateGradeCard(String studentId) {
        adminDaoOperation.generateGradeCard(studentId);
    }
}
