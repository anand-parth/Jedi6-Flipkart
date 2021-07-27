package com.flipkart.crs;

import com.flipkart.bean.payment.*;
import com.flipkart.bean.user.Student;
import com.flipkart.bean.user.User;
import com.flipkart.business.user.StudentInterface;
import com.flipkart.business.user.StudentOperation;
//import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.PaymentMode;
import com.flipkart.constant.UserRole;
import com.flipkart.dao.StudentDaoOperation;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//import static com.flipkart.constant.PaymentMode.CASH;
//import static com.flipkart.constant.PaymentMode.CHEQUE;
import static com.flipkart.constant.PaymentMode.*;
import static com.flipkart.constant.ScanIO.cin;

/**
 *
 *  Class that displays Student Dashboard
 *
 */

public class StudentCRS {

    public static final StudentInterface studentOperation = new StudentOperation();
    private static final Logger LOG = Logger.getLogger(StudentCRS.class);
    private static String studentId;

    /**
     * Method to display student Dashboard.
     * @param userId
     */
    public static void dashboard(String userId) {
        studentId = studentOperation.getStudentIdFromDatabase(userId);
        String formatter = "\n\t";
        String dashBoardMessage = "-------------Student Dashboard-----------------" + formatter + "1. Course Registration"
                + formatter + "2. View All Courses" + formatter + "3. View Registered Courses" + formatter + "4. View Grades" +
                formatter + "5. Pay Fees" + formatter + "6. Log out\n" + "-----------------------------------------------";
        while (true) {
            System.out.println(dashBoardMessage);
            Integer input = cin.nextInt();
            switch (input) {
                case 1:
                    courseRegistration();
                    break;
                case 2:
                    viewAllCourses();
                    break;
                case 3:
                    viewRegisteredCourses();
                    break;
                case 4:
                    viewGrades();
                    break;
                case 5:
                    payFees();
                    break;
                case 6:
                    return;
            }
        }
    }

    /**
     * Method to pay Fees for registration.
     */
    private static void payFees() {
        System.out.println("---------------Fees Payment-------------");
        System.out.println("Choose the mode of fees payment: \n\t 1. Offline\n\t 2. Online");
        Double amount = getTotalFees();
        Pair<FeePayment, PaymentMode> feePayment;
        switch (cin.nextInt()){
            case 1:  feePayment = offlineMenu(); break;
            case 2: feePayment = onlineMenu(); break;
            default:
                throw new IllegalArgumentException("Wrong Choice!!");
        }
        Boolean paymentStatus = studentOperation.payFees(studentId, FeePayment.amount, feePayment);
        if(paymentStatus)
            LOG.info("Payment successful!");
        else
            LOG.info("Payment failed! Try again in some time");
    }

    private static Double getTotalFees() {
        return 0.0;
    }

    private static Pair<FeePayment, PaymentMode> offlineMenu() {
        System.out.println("What payment mode do you prefer? \n\t1. Cash\n\t2. Cheque");
        switch (cin.nextInt()) {
            case 1:
                return new Pair(getCashInstance(), CASH);
            case 2:
                return new Pair(getChequeInstance(), CHEQUE);
            default:
                throw new IllegalArgumentException("Wrong Choice!!");
        }
    }

    private static FeePayment getCashInstance() {
        return new Cash();
    }

    private static FeePayment getChequeInstance() {
        System.out.print("Enter Bank Name: ");
        String bankName = cin.next();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = cin.next();
        System.out.print("Enter Account Number: ");
        String accountNumber = cin.next();
        return new Cheque(bankName,accountHolderName,accountNumber);
    }

    private static Pair<FeePayment, PaymentMode> onlineMenu() {
        System.out.println("What payment mode do you prefer? \n\t1. Netbanking\n\t2. Debit/Credit Card" +
                "\n\t3. UPI\n\t4. Scholarship");
        switch (cin.nextInt()){
            case 1: return new Pair<>(getNetBankingInstance(), NET_BANKING);
            case 2: return new Pair<>(getCardInstance(), CARD);
            case 3: return new Pair<>(getUPIInstance(), UPI);
            case 4: return new Pair<>(getScholarshipInstance(), SCHOLARSHIP);
            default:
                throw new IllegalArgumentException("Wrong Choice!!");
        }
    }

    private static FeePayment getScholarshipInstance() {
        System.out.print("Enter Scholarship amount: ");
        Double amount = cin.nextDouble();
        return new Scholarship(amount);
    }

    private static FeePayment getUPIInstance() {
        System.out.print("Enter UPI Id: ");
        String upiId = cin.next();
        System.out.print("Enter Mobile number: ");
        Long mobileNumber  = cin.nextLong();
        return new UPI(upiId, mobileNumber);
    }

    private static FeePayment getCardInstance() {
        System.out.print("Enter card number: ");
        String cardNumber = cin.next();
        System.out.print("Enter cvv: ");
        Integer cvv = cin.nextInt();
        System.out.print("Enter validTill: ");
        String validTill = cin.next();
        System.out.print("Enter Bank Name: ");
        String bankName = cin.next();
        return new Card(bankName, cardNumber, cvv, validTill);
    }

    private static FeePayment getNetBankingInstance() {
        System.out.print("Enter account number: ");
        String accountNumber = cin.next();
        System.out.print("Enter IFSC Code: ");
        String IFSCCode = cin.next();
        System.out.print("Enter Bank Name: ");
        String bankName = cin.next();
        return new NetBanking(accountNumber, IFSCCode, bankName);
    }

//    private static NetBanking getNetBankingInstance() {
//        System.out.println("Enter NetBanking Details: ");
//        System.out.print("Enter Account Number: ");
//        String accountNumber = cin.next();
//        System.out.print("Enter IFSC Code: ");
//        String ifscCode = cin.next();
//
//
//
//    }


    /**
     * Method to view Grades.
     */
    private static void viewGrades() {
        studentOperation.viewGrades(studentId);
    }

    /**
     * Method to register courses for a student.
     */
    private static void courseRegistration() {
        System.out.println("---------------Course Registration-------------");
        viewAllCourses();
        System.out.print("Enter course choices: ");
        List<String> courseChoices = new ArrayList<>();
        System.out.print("Enter courseId for first mandatory choice: ");
        courseChoices.add(getCourseChoice());
        System.out.print("Enter courseId for second mandatory choice: ");
        courseChoices.add(getCourseChoice());
        System.out.print("Enter courseId for third mandatory choice: ");
        courseChoices.add(getCourseChoice());
        System.out.print("Enter courseId for fourth mandatory choice: ");
        courseChoices.add(getCourseChoice());
        System.out.print("Enter courseId for first optional choice: ");
        courseChoices.add(getCourseChoice());
        System.out.print("Enter courseId for second optional choice: ");
        courseChoices.add(getCourseChoice());
        studentOperation.courseRegistration(studentId, courseChoices);
    }

    /**
     * Method to get choice of courses.
     * @return courses.
     */
    private static String getCourseChoice(){
        return cin.next();
    }

    /**
     * Method to view registered courses for the student.
     */
    private static void viewRegisteredCourses() {
        studentOperation.viewRegisteredCourses(studentId);
    }

    /**
     * Method to view all offered courses in the semester.
     */
    private static void viewAllCourses() {
        studentOperation.viewAllCourses();
    }

    /**
     * Method for student Signup to the system.
     */
    public static void studentSignUp() {
        System.out.println("---------------Student Registration-------------");
        User user = UserCRS.userSignUp(UserRole.STUDENT);
        System.out.println("-----------------------------------------------");
        System.out.print("\n\n\n");
        System.out.println("---------------Academic Information-------------");
        String studentId = studentOperation.generateStudentId();
        System.out.println("StudentId: " + studentId);
        System.out.print("Department: ");
        String department = cin.next();
        System.out.print("Semester: ");
        String semester = cin.next();
        Student student = new Student(user.getloginId(), user.getName(), user.getRole(), user.getPassword(), department,
                studentId, Integer.valueOf(semester), user.getPhoneNumber(), user.getEmailAddress());
        StudentDaoOperation.addStudentToSystem(student);
    }
}
