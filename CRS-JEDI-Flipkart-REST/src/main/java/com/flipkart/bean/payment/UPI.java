package com.flipkart.bean.payment;

public class UPI implements FeePayment {
    private String upiId;
    private Long mobileNumber;

    public UPI(String upiId, Long mobileNumber) {
        this.setUpiId(upiId);
        this.setMobileNumber(mobileNumber);
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

