/**
 *
 */
package com.flipkart.exception;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Exception to check if userId is present already
 *
 */
public class UserIdAlreadyInUseException extends Exception {
    private String userId;

    /**
     * Constructor
     * @param userId
     */
    public UserIdAlreadyInUseException(String userId) {
        this.userId = userId;
    }

    /**
     * Getter function for UserId
     * @return
     */

    public String getUserId() {
        return userId;
    }

    /**
     * set user id
     * @param userId
     */

    public void setProfessorId(String userId) {
        this.userId = userId;
    }

    /**
     * Message returned when exception is thrown
     */

    @Override
    public String getMessage() {
        return "userId: " + userId + " is already in use.";
    }

}