package com.flipkart.business.database;

import java.sql.Connection;

/**
 *
 * @author JEDI-6-Flipkart-G1
 * Interface to make connection to the database
 *
 */

public interface JDBCConnectionInterface {
    Connection getConnection();
}
