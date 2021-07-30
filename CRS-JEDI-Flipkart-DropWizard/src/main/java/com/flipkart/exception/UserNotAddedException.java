package com.flipkart.exception;

/**
 * @author JEDI-6-Flipkart-G1
 * Exception to check if user cannot be adde
 */
public class UserNotAddedException extends Exception {
    private String userId;

    /**
     * Constructor
     *
     * @param userId
     */
    public UserNotAddedException(String userId) {
        this.userId = userId;
    }

    /**
     * Getter function for UserId
     *
     * @return
     */
    public String getUserId() {
        return this.userId;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "UserId: " + userId + " is already in use!";
    }
}