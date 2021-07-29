package com.flipkart.restController;

import com.flipkart.bean.Customer;
import com.flipkart.business.database.JDBCConnectionInterface;
import com.flipkart.business.database.JDBCConnectionOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {


        public void test_func(Customer customer) {
            String sql_query = "INSERT INTO table1 VALUES (?, ?);";
            Connection connection = getConnection();
            try {
                PreparedStatement ps = connection.prepareStatement(sql_query);
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    private Connection getConnection(){
        JDBCConnectionInterface jdbcConnection = JDBCConnectionOperation.getJDBCInstance();
        Connection conn = jdbcConnection.getConnection();
        return conn;
    }

//        private Connection getConnection(){
//        JDBCConnectionOperation jdbcConnection = new JDBCConnectionOperation();
//        JDBCConnectionInterface jb = jdbcConnection.getJDBCInstance();
//        Connection conn = jb.getConnection();
//        return conn;
//    }

}
