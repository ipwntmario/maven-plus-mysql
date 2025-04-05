package com.example;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";
        String password = "pass";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection.isValid(5));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
