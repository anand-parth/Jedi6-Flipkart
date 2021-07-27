package com.flipkart.business.payment;

import com.flipkart.bean.payment.FeePayment;
import com.flipkart.bean.user.Student;
/**
 *
 * @author JEDI-6-Flipkart-G1
 * Abstract class to make fee payments
 *
 */
public abstract class FeePaymentOperation {

    public abstract String sendNotification(Student student);

    public static String InsertQuery(String studentId, String referenceId, String amount, String status){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into FeePayment values ('");
        sql.append(studentId).append("','");
        sql.append(referenceId).append("',");
        sql.append(amount).append(",");
        sql.append(status).append(")");
        return sql.toString();
    }


}
