package com.flipkart.bean.payment;

import java.util.Date;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to store card payment
 *
 */
public class Card implements FeePayment {
    private String cardNumber;
    private Integer cvv;
    private String validTill;
    private String bankName;

    public Card(String bankName, String cardNumber, Integer cvv, String validTill) {
        this.setCardNumber(cardNumber);
        this.setCvv(cvv);
        this.setValidTill(validTill);
        this.setBankName(bankName);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
