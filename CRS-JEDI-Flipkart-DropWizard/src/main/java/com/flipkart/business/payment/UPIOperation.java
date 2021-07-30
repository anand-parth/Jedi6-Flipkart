package com.flipkart.business.payment;

import com.flipkart.bean.payment.UPI;
import com.flipkart.bean.user.Student;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to operate UPI fee payment services
 */
public class UPIOperation extends OnlineOperation {

    /**
     * Method to send notification for completion of fees
     *
     * @param upi
     * @param studentId
     * @return string message for confirmation
     */
    public static String insertQuery(UPI upi, String studentId) {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into UPI values ('");
        sql.append(studentId).append("','");
        sql.append(upi.getUpiId()).append("',");
        sql.append(upi.getMobileNumber()).append(")");
        return sql.toString();
    }

    /**
     * Method to send notification for completion of fees
     *
     * @param student
     * @return string message for confirmation
     */
    @Override
    public String sendNotification(Student student) {

        return "You have successfully paid fees using UPI.";
    }
}
