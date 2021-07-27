package com.flipkart.business.user;

import com.flipkart.bean.user.User;
import com.flipkart.constant.DataGenerator;
import com.flipkart.constant.UserRole;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import javafx.util.Pair;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserOperation implements UserInterface{

    private static final Logger LOG = Logger.getLogger(UserOperation.class);
    private static final UserDaoInterface userDaoOperation = new UserDaoOperation();

    @Override
    public User addUserToSystem(User user) {
        return userDaoOperation.addUserToSystem(user);
    }

    public Pair<Boolean, UserRole> verifyCredentials(String userId, String password){
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

    public Integer sendOTPToMail(String emailAddress) {
        Integer OTP = DataGenerator.generateOTP();
        // send mail here
        return OTP;
    }

    @Override
    public List<String> getAllUserIdList() {
        return userDaoOperation.getAllUserIdList();
    }

    @Override
    public void printUserDetails(String userId) {
        Map<String, String > studentDetails = userDaoOperation.printUserDetails(userId);
        studentDetails.forEach((key,value) -> System.out.println(key + " : " + value));
    }

    @Override
    public void updatePassword(String userId, String password) {
        userDaoOperation.updatePassword(userId, password);
    }
}
