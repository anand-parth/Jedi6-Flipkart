package com.flipkart.bean.payment;

public class Cheque implements FeePayment {
    private String bankName;
    private String accountHolderName;
    private String accountNumber;

    public Cheque(String bankName, String accountHolderName, String accountNumber) {
        this.setBankName(bankName);
        this.setAccountHolderName(accountHolderName);
        this.setAccountNumber(accountNumber);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
