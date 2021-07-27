package com.flipkart.business.payment;

import com.flipkart.bean.payment.Card;
import com.flipkart.bean.user.Student;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 *
 */
public class CardOperation extends OnlineOperation {

    /**
     * Method to add a query for details from card
     * @param card
     * @return query in String type
     */

    public static String InsertQuery(Card card, String studentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into Card values('");
        sql.append(studentId).append("','");
        sql.append(card.getCardNumber()).append("',");
        sql.append(card.getValidTill()).append(")");
        return sql.toString();
    }

    /**
     * Method to send notification for completion of fees
     * @param student
     * @return string message for confirmation
     */
    @Override

    public String sendNotification(Student student) {

        return "You have successfully paid fees using Card.";
    }
}
