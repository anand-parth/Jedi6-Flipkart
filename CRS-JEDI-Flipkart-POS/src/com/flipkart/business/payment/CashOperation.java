package com.flipkart.business.payment;

import com.flipkart.bean.payment.Cash;
import com.flipkart.bean.user.Student;
/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to confirm cash payment
 *
 */
public class CashOperation extends OfflineOperation{

    @Override
    public String sendNotification(Student student) {

        return "You have successfully paid fees using Cash";
    }
}
