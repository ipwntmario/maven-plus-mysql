package com.example;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        Connection connection = MySQLConnectionUtility.getConnection();
        try {
            System.out.println(connection.isValid(5));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
