package com.flipkart.bean.academics;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to store course information
 *
 */
public class Course{
    private String courseId;
    private String courseName;
    private String courseFaculty;

    /**
     * Parameterized constructor
     * @param courseId: CourseId
     * @param courseName: Course Name
     */
    public Course(String courseId, String courseName) {
        this.setCourseId(courseId);
        this.setCourseName(courseName);
    }

    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseFaculty() {
        return courseFaculty;
    }
    public void setCourseFaculty(String courseFaculty) {
        this.courseFaculty = courseFaculty;
    }
}
