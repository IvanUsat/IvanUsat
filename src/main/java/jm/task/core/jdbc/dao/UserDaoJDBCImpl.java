package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            String sql = "CREATE TABLE User (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age INT)";
            statement.executeUpdate(sql);
            System.out.println("Table is create");
        }finally {
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
    }


    public void dropUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "DROP TABLE if exists User";
            statement.executeUpdate(sql);
            System.out.println("Table is drop");
        }finally {
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "INSERT INTO User (name, lastname, age) VALUES ('" + name + "','" + lastName + "', '" + age +"')";
            statement.executeUpdate(sql);
            String Sql = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(Sql);
            System.out.println("Users save");
            while (rs.next()) {
                name = rs.getString("name");
                String lastname = rs.getString("lastname");
                age = rs.getByte("age");
            }
        } finally{
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "DELETE FROM User WHERE id = 1";
            statement.executeUpdate(sql);
            String Sql = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(Sql);
            System.out.println("User remove");
            while (rs.next()) {
                Long id1 = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                Byte age = rs.getByte("age");
            }
            } finally{
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        }


    public List<User> getAllUsers() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try{
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Users getting");
            while(rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                Byte age = rs.getByte("age");
                users.add(new User(id, name, lastname, age));
            }
            } finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        return users;
    }


    public void cleanUsersTable() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "TRUNCATE TABLE User ";
            statement.executeUpdate(sql);
            String Sql = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(Sql);
            System.out.println("Table clean");
        } finally{
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}