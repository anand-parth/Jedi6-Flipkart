package com.flipkart.business.user;

import com.flipkart.bean.user.User;
import com.flipkart.constant.CustomEntry;
import com.flipkart.constant.DataGenerator;
import com.flipkart.constant.UserRole;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author JEDI-6-Flipkart-G1
 * Class to complete Card Operation
 */

public class UserOperation implements UserInterface {

    private static final Logger LOG = Logger.getLogger(UserOperation.class);
    private static final UserDaoInterface userDaoOperation = new UserDaoOperation();

    /**
     * Method to add a new user to system with given object instance
     *
     * @param user
     * @return object instance of the new user
     */
    @Override
    public User addUserToSystem(User user) {
        return userDaoOperation.addUserToSystem(user);
    }

    /**
     * Method to verify credentials of user during login
     *
     * @param userId
     * @param password
     * @return pair of verification status and user role type
     */

    public CustomEntry<Boolean, UserRole> verifyCredentials(String userId, String password) {
        try {
            return userDaoOperation.verifyCredentials(userId, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String printDetails(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("UserID: ").append(user.getloginId()).append("\n");
        sb.append("Name: ").append(user.getName()).append("\n");
        sb.append("Role: ").append(user.getRole().toString()).append("\n");
        sb.append("Mobile No.: ").append(user.getPhoneNumber()).append("\n");
        sb.append("Email Address: ").append(user.getEmailAddress()).append("\n");
        return sb.toString();
    }

    public static String userToInsertQuery(User user) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into User values('");
        sql.append(user.getloginId()).append("','");
        sql.append(user.getName()).append("',");
        sql.append(user.getRole().toInt()).append(",'");
        sql.append(user.getPhoneNumber()).append("','");
        sql.append(user.getEmailAddress()).append("')");
        return sql.toString();
    }

    /**
     * Method to send OTP over mail to re-enter forgotten password
     *
     * @param emailAddress
     * @return integer One-time-password
     */


    public Integer sendOTPToMail(String emailAddress) {
        Integer OTP = DataGenerator.generateOTP();
        // send mail here
        return OTP;
    }

    /**
     * Method to get a list of all User IDs in the system
     *
     * @return List of User IDs in the system
     */

    @Override
    public List<String> getAllUserIdList() {
        return userDaoOperation.getAllUserIdList();
    }

    /**
     * Method to print list of all users in the system
     *
     * @param userId
     * @return
     */

    @Override
    public Map<String, String> printUserDetails(String userId) {
        Map<String, String> studentDetails = userDaoOperation.printUserDetails(userId);
        studentDetails.forEach((key, value) -> System.out.println(key + " : " + value));
        return studentDetails;
    }

    /**
     * Method to update user password in case of failure to login
     *
     * @param userId
     * @param password
     */
    @Override
    public void updatePassword(String userId, String password) {
        userDaoOperation.updatePassword(userId, password);
    }
}
