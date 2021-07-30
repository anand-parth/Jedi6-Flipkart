package com.flipkart.business.payment;

import com.flipkart.bean.payment.NetBanking;
import com.flipkart.bean.user.Student;


/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to complete net banking operations for fee payment
 *
 */
public class NetBankingOperation extends OnlineOperation {

    /**
     * Method to send notification for completion of fees
     * @param netBanking
     * @param studentId
     * @return string message for confirmation
     */
    public static String insertQuery(NetBanking netBanking, Student studentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into NetBanking values ('");
        sql.append(studentId).append("','");
        sql.append(netBanking.getAccountNumber()).append("','");
        sql.append(netBanking.getIFSCCode()).append("')");
        return sql.toString();
    }

    /**
     * Method to send notification for completion of fees
     * @param student
     * @return string message for confirmation
     */
    @Override
    public String sendNotification(Student student) {

        return "You have successfully paid fees using NetBanking.";
    }
}
