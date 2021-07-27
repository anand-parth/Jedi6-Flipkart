package com.flipkart.business.user;

import com.flipkart.bean.payment.FeePayment;
import com.flipkart.constant.PaymentMode;
import javafx.util.Pair;

import java.util.List;

public interface StudentInterface {


    /**
     * Method to display student details for given StudentID
     * @param studentId
     */
    void displayStudent(String studentId);

    /**
     * Method to view all courses in catalog
     */

    void viewAllCourses();

    /**
     * Method to get studentID corresponding to UserId
     * @param userId
     * @return StudentID corresponding to UserID
     */

    String getStudentIdFromDatabase(String userId);

    /**
     * Method to view registered courses of the given student
     * @param studentId
     */

    void viewRegisteredCourses(String studentId);

    /**
     * Method to view grades of the given student
     * @param studentId
     */

    void viewGrades(String studentId);

    /**
     * Method to deposit fees for the given student
     * @param studentId
     * @param amount
     * @param feePayment
     * @return
     */

    Boolean payFees(String studentId, Double amount, Pair<FeePayment, PaymentMode> feePayment);

    /**
     * Method to generate StudentID for student
     * @return StudentID
     */

    String generateStudentId();

    /**
     * Method to register courses with given choices for given student
     * @param studentId
     * @param courseChoices
     */

    void courseRegistration(String studentId, List<String> courseChoices);
}
