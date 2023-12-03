package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public Connection getConnection(String url, String userName, String password) {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return connection;
    }

}





