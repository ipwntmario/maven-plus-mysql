package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionUtility {
        static String url = "jdbc:mysql://localhost:3306/jdbc";
        static String username = "root";
        static String password = "pass";

        static Connection getConnection() {
            Connection connection = null;

            try {
                connection = DriverManager.getConnection(
                        url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
        }
}
