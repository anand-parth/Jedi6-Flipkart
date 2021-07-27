package com.flipkart.business.payment;

import com.flipkart.bean.payment.UPI;
import com.flipkart.bean.user.Student;
/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to operate UPI fee payment services
 *
 */
public class UPIOperation extends OnlineOperation {

    public static String InsertQuery(UPI upi, String studentId) {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into UPI values ('");
        sql.append(studentId).append("','");
        sql.append(upi.getUpiId()).append("',");
        sql.append(upi.getMobileNumber()).append(")");
        return sql.toString();
    }


    @Override
    public String sendNotification(Student student) {

        return "You have successfully paid fees using UPI.";
    }
}
