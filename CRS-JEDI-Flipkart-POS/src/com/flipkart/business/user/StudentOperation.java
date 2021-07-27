package com.flipkart.business.user;

import com.flipkart.bean.payment.FeePayment;
import com.flipkart.bean.user.Student;
import com.flipkart.constant.PaymentMode;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void viewAllCourses() {
        Map<String, HashMap<String, String>> coursesDetails = studentDaoOperation.viewAllCourses();
        for (String courseId : coursesDetails.keySet()) {
            Map<String,String> course = coursesDetails.get(courseId);
            System.out.println("courseId: " + courseId);
            for(String key:course.keySet()){
                System.out.println(key +": "+course.get(key));
            }
            System.out.println();
        }
    }

    @Override
    public String getStudentIdFromDatabase(String userId) {
        return studentDaoOperation.getStudentIdFromDatabase(userId);
    }

    @Override
    public void viewRegisteredCourses(String studentId) {
        List<String> courseIdList = studentDaoOperation.viewRegisteredCourses(studentId);
        System.out.println("Courses registered are: ");
        int cnt = 0;
        for(String courseId : courseIdList){
            System.out.print(++cnt + ". "+ courseId);
        }
    }

    @Override
    public void viewGrades(String studentId) {
        Map<String, String> grades = studentDaoOperation.viewGrades(studentId);
    }

    @Override
    public Boolean payFees(String studentId, Double amount, Pair<FeePayment, PaymentMode> feePayment) {
        return true;
    }

    @Override
    public String generateStudentId() {
        return studentDaoOperation.generateStudentId();
    }

    @Override
    public void courseRegistration(String studentId, List<String> courseChoices) {
        studentDaoOperation.courseRegistration(studentId, courseChoices);
    }

    @Override
    public void displayStudent(String studentId) {
        Map<String, String> studentDetails = studentDaoOperation.getStudentDetails(studentId);
        studentDetails.forEach((key, value) -> System.out.println(key + ": "+value));
    }
}
