package com.flipkart.bean.database;

import com.flipkart.business.database.JDBCConnectionInterface;

import java.sql.*;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Class to establish JDBCConnection
 *
 */
public class JDBCConnection implements JDBCConnectionInterface {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection conn;

    private String databaseURL;
    private String userName;
    private String password;

    /**
     * Parameterized constructor
     * @param databaseURL: JDBC connection database
     * @param userName: JDBC connection username
     * @param password: JDBC connection password
     */
    public JDBCConnection(String databaseURL, String userName, String password) {
        this.setDatabaseURL(databaseURL);
        this.setUserName(userName);
        this.setPassword(password);
        this.setConnection();
    }

    private void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(databaseURL, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }
}
