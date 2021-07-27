package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check generated gradecard
 *
 */
public class GradeCardNotGeneratedException extends Exception {

    public GradeCardNotGeneratedException(){
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage()
    {
        return "Gradecard hasn't been generated yet, contact admin!";
    }
}
