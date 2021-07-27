package com.flipkart.bean.user;

import com.flipkart.constant.UserRole;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Admin class
 *
 */
public class Admin extends User {

    private String adminId;

    /**
     * Parameterized constructor
     * @param adminId: Admin Id
     * @param userId: User Id
     * @param name: Admin name
     * @param role: User role
     * @param password: User login password
     * @param phoneNumber: Admin phoneNumber
     * @param emailAddress: email address
     *
     */
    public Admin(String adminId, String userId, String name, UserRole role, String password, Long phoneNumber, String emailAddress) {
        super(userId, name, role, password, phoneNumber, emailAddress);
        this.setAdminId(adminId);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}