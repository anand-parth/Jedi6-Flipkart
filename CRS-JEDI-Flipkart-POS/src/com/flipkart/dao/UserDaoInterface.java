package com.flipkart.dao;

import com.flipkart.bean.user.User;
import com.flipkart.constant.UserRole;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JEDI-06
 * Interface for User Operations
 *
 */
public interface UserDaoInterface {

    /**
     * Method to verify Login credentials for a user on the portal.
     * @param userId
     * @param password
     * @return pair of boolean value and userRole type which denote if the credentials are verified for a particular role.
     * @throws SQLException
     */
    Pair<Boolean, UserRole> verifyCredentials(String userId, String password) throws SQLException;

    /**
     * Method to get user IDs for all users present in the system.
     * @return List of user IDs
     */
    List<String> getAllUserIdList();

    /**
     * Method to print User Details corresponding to a user ID.
     * @param userId
     * @return map from userID to their details.
     */
    Map<String, String> printUserDetails(String userId);

    /**
     * Method to update login password for a user.
     * @param userId
     * @param password
     */
    void updatePassword(String userId, String password);

    /**
     * Method to add a user to the system.
     * @param user
     * @return User object.
     */
    User addUserToSystem(User user);
}
