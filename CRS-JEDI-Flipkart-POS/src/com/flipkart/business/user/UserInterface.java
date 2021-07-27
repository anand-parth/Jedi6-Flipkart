package com.flipkart.business.user;

import com.flipkart.bean.user.User;
import com.flipkart.constant.UserRole;
import javafx.util.Pair;

import java.util.List;

public interface UserInterface {

    /**
     * Method to add a new user to system with given object instance
     * @param userInstance
     * @return object instance of the new user
     */
    User addUserToSystem(User userInstance);

    /**
     * Method to verify credentials of user during login
     * @param userId
     * @param password
     * @return pair of verification status and user role type
     */

    Pair<Boolean, UserRole> verifyCredentials(String userId, String password);

    /**
     * Method to send OTP over mail to re-enter forgotten password
     * @param emailAddress
     * @return integer One-time-password
     */

    Integer sendOTPToMail(String emailAddress);

    /**
     * Method to get a list of all User IDs in the system
     * @return List of User IDs in the system
     */

    List<String> getAllUserIdList();

    /**
     * Method to print list of all users in the system
     * @param userId
     */

    void printUserDetails(String userId);

    /**
     * Method to update user password in case of failure to login
     * @param userId
     * @param password
     */

    void updatePassword(String userId, String password);
}
