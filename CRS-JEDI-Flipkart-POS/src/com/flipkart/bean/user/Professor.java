package com.flipkart.bean.user;

import com.flipkart.constant.Designation;
import com.flipkart.constant.UserRole;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Professor class
 *
 */
public class Professor extends User {
    private String professorId;
    private String department;
    private Designation designation;

    /**
     * Parameterized constructor
     * @param professorId: Professor Id
     * @param department: Professor's department
     * @param designation: Professor's designation
     * @param userId: User Id
     * @param name: Professor name
     * @param role: User role
     * @param password: User login password
     * @param phoneNumber: Admin phoneNumber
     * @param emailAddress: email address
     *
     */
    public Professor(String professorId, String department, Designation designation, String userId, String name, UserRole role, String password, Long phoneNumber, String emailAddress) {
        super(userId, name, role, password, phoneNumber, emailAddress);
        this.setProfessorId(professorId);
        this.setDepartment(department);
        this.setDesignation(designation);
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }
}
