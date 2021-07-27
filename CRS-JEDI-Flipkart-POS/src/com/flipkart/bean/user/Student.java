package com.flipkart.bean.user;

import com.flipkart.constant.UserRole;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Student class
 *
 */
public class Student extends User {
    private String studentId;
    private String department;
    private Integer semester;

    /**
     * Parameterized constructor
     * @param loginId: Student logi
     * @param name: Student name
     * @param role: User role
     * @param password: User login password
     * @param department: Student department
     * @param studentId: User Id
     * @param sem: Student semester
     * @param phoneNumber: Admin phoneNumber
     * @param emailAddress: email address
     *
     */
    public Student(String loginId, String name, UserRole role, String password,
                   String department, String studentId, Integer sem, Long phoneNumber, String emailAddress) {
        super(loginId, name, role, password, phoneNumber, emailAddress);
        this.setStudentId(studentId);
        this.setDepartment(department);
        this.setSemester(sem);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
