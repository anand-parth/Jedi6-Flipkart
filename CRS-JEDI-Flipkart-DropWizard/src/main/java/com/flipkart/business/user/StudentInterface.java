package com.flipkart.business.user;

import com.flipkart.bean.payment.FeePayment;
import com.flipkart.bean.user.Student;
import com.flipkart.constant.CustomEntry;
import com.flipkart.constant.PaymentMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 */

public interface StudentInterface {


    /**
     * Method to display student details for given StudentID
     *
     * @param studentId
     */
    void displayStudent(String studentId);

    /**
     * Method to view all courses in catalog
     *
     * @return
     */
    Map<String, HashMap<String, String>> viewAllCourses();

    /**
     * Method to get studentID corresponding to UserId
     *
     * @param userId
     * @return StudentID corresponding to UserID
     */
    String getStudentIdFromDatabase(String userId);

    /**
     * Method to view registered courses of the given student
     *
     * @param studentId
     * @return
     */
    List<String> viewRegisteredCourses(String studentId);

    /**
     * Method to view grades of the given student
     *
     * @param studentId
     * @return
     */
    Map<String, String> viewGrades(String studentId);

    /**
     * Method to deposit fees for the given student
     *
     * @param studentId
     * @param amount
     * @param feePayment
     * @return
     */
    Boolean payFees(String studentId, Double amount, CustomEntry<FeePayment, PaymentMode> feePayment);

    /**
     * Method to generate StudentID for student
     *
     * @return StudentID
     */
    String generateStudentId();

    /**
     * Method to register courses with given choices for given student
     *
     * @param studentId
     * @param courseChoices
     */
    void courseRegistration(String studentId, List<String> courseChoices);

    /**
     * Method to add student to system.
     *
     * @param student
     */
    void addStudentToSystem(Student student);
}
