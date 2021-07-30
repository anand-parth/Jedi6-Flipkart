package com.flipkart.business.user;

import com.flipkart.bean.payment.FeePayment;
import com.flipkart.bean.user.Student;
import com.flipkart.constant.CustomEntry;
import com.flipkart.constant.PaymentMode;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 */

public class StudentOperation implements StudentInterface {

    private static Logger LOG = Logger.getLogger(UserOperation.class);
    private static final StudentDaoInterface studentDaoOperation = new StudentDaoOperation();

    public static String studentToInsertQuery(Student student) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Student values('");
        sql.append(student.getloginId()).append("','");
        sql.append(student.getStudentId()).append("','");
        sql.append(student.getDepartment()).append("',");
        sql.append(student.getSemester()).append(",");
        sql.append("false)");
        return sql.toString();
    }

    /**
     * Method to view all courses in catalog
     *
     * @return
     */
    @Override
    public Map<String, HashMap<String, String>> viewAllCourses() {
        Map<String, HashMap<String, String>> coursesDetails = studentDaoOperation.viewAllCourses();
        for (String courseId : coursesDetails.keySet()) {
            Map<String, String> course = coursesDetails.get(courseId);
            System.out.println("courseId: " + courseId);
            for (String key : course.keySet()) {
                System.out.println(key + ": " + course.get(key));
            }
            System.out.println();
        }
        return coursesDetails;
    }

    /**
     * Method to get studentID corresponding to UserId
     *
     * @param userId
     * @return StudentID corresponding to UserID
     */
    @Override
    public String getStudentIdFromDatabase(String userId) {
        return studentDaoOperation.getStudentIdFromDatabase(userId);
    }

    /**
     * Method to view registered courses of the given student
     *
     * @param studentId
     * @return
     */
    @Override
    public List<String> viewRegisteredCourses(String studentId) {
        List<String> courseIdList = studentDaoOperation.viewRegisteredCourses(studentId);
        System.out.println("Courses registered are: ");
        int cnt = 0;
        for (String courseId : courseIdList) {
            System.out.print(++cnt + ". " + courseId);
        }
        return courseIdList;
    }

    /**
     * Method to view grades of the given student
     *
     * @param studentId
     * @return
     */
    @Override
    public Map<String, String> viewGrades(String studentId) {
        Map<String, String> grades = studentDaoOperation.viewGrades(studentId);
        return grades;
    }

    /**
     * Method to deposit fees for the given student
     *
     * @param studentId
     * @param amount
     * @param feePayment
     * @return
     */
    @Override
    public Boolean payFees(String studentId, Double amount, CustomEntry<FeePayment, PaymentMode> feePayment) {
        return true;
    }

    /**
     * Method to generate StudentID for student
     *
     * @return StudentID
     */
    @Override
    public String generateStudentId() {
        return studentDaoOperation.generateStudentId();
    }

    /**
     * Method to register courses with given choices for given student
     *
     * @param studentId
     * @param courseChoices
     */
    @Override
    public void courseRegistration(String studentId, List<String> courseChoices) {
        studentDaoOperation.courseRegistration(studentId, courseChoices);
    }

    /**
     * Method to add student to system.
     *
     * @param student
     */
    @Override
    public void addStudentToSystem(Student student) {
        studentDaoOperation.addStudentToSystem(student);
    }

    /**
     * Method to display student details for given StudentID
     *
     * @param studentId
     */
    @Override
    public void displayStudent(String studentId) {
        Map<String, String> studentDetails = studentDaoOperation.getStudentDetails(studentId);
        studentDetails.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
