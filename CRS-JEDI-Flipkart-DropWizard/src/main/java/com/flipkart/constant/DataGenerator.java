package com.flipkart.constant;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to generate Ids
 */
public class DataGenerator {
    /**
     * generateOTP funtion
     * @return
     */
    public static Integer generateOTP() {
        return (int) (Math.random() * 1000000);
    }

    /**
     * generateStudentId funtion
     * @return
     */
    public static String generateStudentId() {
        return "";
    }

    /**
     * generateProfessorId funtion
     * @return
     */
    public static String generateProfessorId() {
        return "";
    }
}
