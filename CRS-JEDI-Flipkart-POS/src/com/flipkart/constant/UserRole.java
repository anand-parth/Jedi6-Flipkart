package com.flipkart.constant;

public enum UserRole {
    ADMIN, PROFESSOR, STUDENT;

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

    public Integer toInt() {
        switch(this){
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
