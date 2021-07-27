package com.flipkart.business.payment;

import com.flipkart.bean.payment.Cheque;
import com.flipkart.bean.user.Student;
/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to complete payment via cheque operations
 *
 */
public class ChequeOperation extends OfflineOperation{

    public static String InsertQuery(Cheque cheque, String studentId){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Cheque values ('");
        sql.append(studentId).append("','");
        sql.append(cheque.getBankName()).append("','");
        sql.append(cheque.getAccountHolderName()).append("','");
        sql.append(cheque.getAccountNumber()).append("')");

        return sql.toString();
    }

    @Override
    public String sendNotification(Student student) {

        return "You have successfully paid fees using Cheque";
    }
}
