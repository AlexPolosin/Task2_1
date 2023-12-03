package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/lesson1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Lendondonovan1";
    Util util = new Util();
    Connection connection = util.getConnection(URL, USERNAME, PASSWORD);

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (idUsers INT AUTO_INCREMENT PRIMARY KEY, nameUsers VARCHAR(255)," +
                    " lastNameUsers VARCHAR(255), ageUsers INT(3))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users " +
                "(nameUsers, lastNameUsers, ageUsers) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE ?")) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT idUsers, nameUsers, lastNameUsers, ageUsers FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("idUsers"));
                user.setName(resultSet.getString("nameUsers"));
                user.setLastName(resultSet.getString("lastNameUsers"));
                user.setAge(resultSet.getByte("ageUsers"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
