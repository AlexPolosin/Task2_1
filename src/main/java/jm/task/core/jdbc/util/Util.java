package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/lesson1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Lendondonovan1";

    public Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return connection;
    }

}





