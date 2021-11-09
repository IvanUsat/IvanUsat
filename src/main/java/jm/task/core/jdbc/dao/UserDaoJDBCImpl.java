package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE User (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age INT)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Table is create");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE if exists User");
            System.out.println("Table is drop");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO User (name, lastName, age) VALUES (?, ?, ?)")) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("Table is saved");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM User WHERE id = 1");
            System.out.println("User by " + id + " remove");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> arr = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
             ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM User")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                arr.add(user);
                connection.commit();
                System.out.println("Users is getting");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return arr;
    }


    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE User");
            System.out.println("Table clean");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}