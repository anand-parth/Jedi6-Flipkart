package com.flipkart.bean.user;

import com.flipkart.business.user.UserOperation;
import com.flipkart.constant.UserRole;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * User class
 *
 */
public class User {
    private String loginId;
    private String name;
    private UserRole role;
    private String password;
    private Long phoneNumber;
    private String emailAddress;

    /**
     * Parameterized constructor
     * @param loginId: Login Id
     * @param name: Admin name
     * @param role: User role
     * @param password: User login password
     * @param phoneNumber: Admin phoneNumber
     * @param emailAddress: email address
     *
     */
    public User(String loginId, String name, UserRole role, String password, Long phoneNumber, String emailAddress) {
        this.setloginId(loginId);
        this.setName(name);
        this.setRole(role);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setEmailAddress(emailAddress);
    }

    public String getloginId() {
        return loginId;
    }
    public void setloginId(String loginId) {
        this.loginId = loginId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String printDetails() {
        return UserOperation.printDetails(this);
    }
}
