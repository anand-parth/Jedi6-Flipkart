package com.flipkart.constant;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to operate UPI fee payment services
 */
public enum Grade {
    ALPHA, ALPHA_MINUS, BETA, BETA_MINUS, CHARLIE, CHARLIE_MINUS, DELTA, DELTA_MINUS, FAIL;

    /**
     * Convert Grade to string
     * @return
     */
    public String toString() {
        switch (this) {
            case ALPHA:
                return "A";
            case ALPHA_MINUS:
                return "A-";
            case BETA:
                return "B";
            case BETA_MINUS:
                return "B-";
            case CHARLIE:
                return "C";
            case CHARLIE_MINUS:
                return "C-";
            case DELTA:
                return "D";
            case DELTA_MINUS:
                return "D-";
            case FAIL:
                return "F";
            default:
                throw new IllegalArgumentException("Grade not valid!!!");
        }
    }

}
