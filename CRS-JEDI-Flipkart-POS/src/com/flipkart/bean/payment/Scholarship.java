package com.flipkart.bean.payment;

public class Scholarship implements FeePayment {
    private Double amount;

    public Scholarship(Double amount){
        this.setAmount(amount);
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }
}
