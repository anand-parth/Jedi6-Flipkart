package com.flipkart.constant;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to operate UPI fee payment services
 */
public enum UserRole {
    ADMIN, PROFESSOR, STUDENT;

    /**
     * Convert Grade to string
     * @param userRole
     * @return
     */
    public static UserRole getRole(Integer userRole) {
        switch (userRole) {
            case 0:
                return ADMIN;
            case 1:
                return PROFESSOR;
            case 2:
                return STUDENT;
            default:
                throw new IllegalArgumentException("User Role not recognized!!");
        }
    }

    /**
     * Convert Grade to string
     * @return
     */
    public Integer toInt() {
        switch (this) {
            case ADMIN:
                return 0;
            case PROFESSOR:
                return 1;
            case STUDENT:
                return 2;
            default:
                throw new IllegalArgumentException("Invalid UserRole");
        }
    }
}
