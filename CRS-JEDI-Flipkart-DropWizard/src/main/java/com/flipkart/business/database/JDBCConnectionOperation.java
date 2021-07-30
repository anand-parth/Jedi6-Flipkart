package com.flipkart.business.database;

import com.flipkart.bean.database.JDBCConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static com.flipkart.constant.DatabaseTables.*;

public class JDBCConnectionOperation {

    private static String databaseUrl;
    private static String userName;
    private static String password;

    public static JDBCConnection getJDBCInstance() {
        getConnectionParameters();
        return new JDBCConnection(databaseUrl, userName, password);
    }

    private static void getConnectionParameters() {
        Properties props = parseConfigurationProperties();
        databaseUrl = props.getProperty(DATABASE_URL);
        userName = props.getProperty(DB_USERNAME);
        password = props.getProperty(DB_PASSWORD);
    }

    private static Properties parseConfigurationProperties() {
        Properties props = new Properties();
        try {
            InputStream inputStream = new FileInputStream(DATABASE_CONFIG_FILE_PATH);
            props.load(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }
}
