package jm.task.core.jdbc.util;




import jm.task.core.jdbc.Main;

import java.sql.*;

public class Util{
    private static final String HOST = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    Statement statement = null;
    Connection connection = null;
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("Connection OK");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}



