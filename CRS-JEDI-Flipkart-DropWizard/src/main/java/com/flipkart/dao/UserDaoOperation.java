package com.flipkart.dao;

import com.flipkart.bean.user.User;
import com.flipkart.business.database.JDBCConnectionInterface;
import com.flipkart.business.database.JDBCConnectionOperation;
import com.flipkart.business.user.UserOperation;
import com.flipkart.constant.CustomEntry;
import com.flipkart.constant.UserRole;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.flipkart.constant.DatabaseTables.CREDENTIALS_TABLES;
import static com.flipkart.constant.DatabaseTables.USER_TABLE;

/**
 * @author JEDI-06
 * Interface for User Operations
 */
public class UserDaoOperation implements UserDaoInterface {

    private static final Logger LOG = Logger.getLogger(UserDaoOperation.class);

    /**
     * Method to add a user to the system.
     *
     * @param user
     * @return User object.
     */
    @Override
    public User addUserToSystem(User user) {
        Connection conn = getConnection();
        String sql = UserOperation.userToInsertQuery(user);
        executeSQLUpdateQuery(conn, sql);
        sql = addToCredentialsTable(user.getloginId(), user.getPassword());
        executeSQLUpdateQuery(conn, sql);
        LOG.info("User successfully added to the System!!");
        return user;
    }

    /**
     * Method to verify Login credentials for a user on the portal.
     *
     * @param userId
     * @param password
     * @return pair of boolean value and userRole type which denote if the credentials are verified for a particular role.
     * @throws SQLException
     */
    @Override
    public CustomEntry<Boolean, UserRole> verifyCredentials(String userId, String password) throws SQLException {
        Boolean isloggedIn = false;
        UserRole role = null;
        Connection conn = getConnection();
        String sql = verifyFromCredentialsTable(userId, password);
        PreparedStatement stmt = conn.prepareStatement(sql);
        Integer rowCount = 0;
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            ++rowCount;
        }
        if (rowCount > 0) {
            isloggedIn = true;
            sql = getRoleFromUserTableQuery(userId);
            result = executeSQLSelectQuery(conn, sql);
            while (result.next()) {
                role = UserRole.getRole(result.getInt("UserRole"));
            }
            if (role.equals(UserRole.STUDENT)) {
                Boolean isApproved = false;
                sql = "select isApproved from student where userId='" + userId + "'";
                result = executeSQLSelectQuery(conn, sql);
                while (result.next()) {
                    isApproved = result.getBoolean("isApproved");
                }
                if (!isApproved) {
                    LOG.error("Approval Pending, contact Admin!!");
                    isloggedIn = false;
                }
            }
        }
        return new CustomEntry<>(isloggedIn, role);
    }

    /**
     * Method to get user IDs for all users present in the system.
     *
     * @return List of user IDs
     */
    @Override
    public List<String> getAllUserIdList() {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT userId FROM User;");
            ResultSet result = stmt.executeQuery();
            List<String> userList = new ArrayList<String>();
            while (result.next()) {
                userList.add(result.getString(1));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to print User Details corresponding to a user ID.
     *
     * @param userId
     * @return map from userID to their details.
     */
    @Override
    public Map<String, String> printUserDetails(String userId) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE userId=?;");
            stmt.setString(1, userId);
            ResultSet result = stmt.executeQuery();
            Map<String, String> userDetails = new HashMap<>();
            while (result.next()) {
                HashMap<String, String> user = new HashMap<>();
                userDetails.put("userId", userId);
                userDetails.put("userName", result.getString(2));
                userDetails.put("userRole", result.getString(3));
                userDetails.put("phoneNumber", result.getString(4));
                userDetails.put("emailAddress", result.getString(5));
            }
            return userDetails;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to update login password for a user.
     *
     * @param userId
     * @param password
     */
    @Override
    public void updatePassword(String userId, String password) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Credentials WHERE userId=?;");
            stmt.setString(1, userId);
            Integer rowCount = 0;
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                ++rowCount;
            }
            if (rowCount == 0) {
                throw new UserNotFoundException(userId);
            }
            stmt = conn.prepareStatement("UPDATE Credentials SET password=? WHERE userId=?; ");
            stmt.setString(1, password);
            stmt.setString(2, userId);
            stmt.executeUpdate();
            LOG.info("Password Updated!!");
        } catch (SQLException | UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getRoleFromUserTableQuery(String userId) {
        return "select userRole from " + USER_TABLE + " where userId = '" + userId + "'";
    }

    private static String verifyFromCredentialsTable(String userId, String password) {
        return "select * from " + CREDENTIALS_TABLES + " where " + " userId = '" + userId + "' and password = '" + password + "'";
    }

    /**
     * Method to get string insert string for credentials table
     *
     * @param userId
     * @param password
     * @return String
     */
    private static String addToCredentialsTable(String userId, String password) {
        return "insert into " + CREDENTIALS_TABLES + " values( '" + userId + "' , '" + password + "')";
    }

    /**
     * Method to get JDBC connection instance.
     *
     * @return connection
     */
    private static Connection getConnection() {
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        return conn;
    }

    /**
     * Method to execute sql select query
     *
     * @param conn
     * @param sql
     * @return ResultSet
     */
    private static ResultSet executeSQLSelectQuery(Connection conn, String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to execute sql update query
     *
     * @param conn
     * @param sql
     * @return ResultSet
     */
    private static Integer executeSQLUpdateQuery(Connection conn, String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
