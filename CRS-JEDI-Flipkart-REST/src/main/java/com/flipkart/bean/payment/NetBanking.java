package com.flipkart.bean.payment;

public class NetBanking implements FeePayment {
    
    private String accountNumber;
    private String IFSCCode;
    private String bankName;

    public NetBanking(String accountNumber, String IFSCCode, String bankName) {
        this.setAccountNumber(accountNumber);
        this.setIFSCCode(IFSCCode);
        this.setBankName(bankName);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getAmount(){
        return FeePayment.amount;
    }
}
