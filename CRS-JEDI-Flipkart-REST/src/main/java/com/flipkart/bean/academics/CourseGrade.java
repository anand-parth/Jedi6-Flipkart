package com.flipkart.bean.academics;

import com.flipkart.constant.Grade;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to store course grades
 *
 */
public class CourseGrade {
    private Grade grade;
    private String courseId;

    /**
     * Parameterized constructor
     * @param grade: course grade
     * @param courseId: CourseId
     */
    public CourseGrade(Grade grade, String courseId){
        this.grade = grade;
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
