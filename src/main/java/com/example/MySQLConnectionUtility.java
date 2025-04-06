package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionUtility {
    static final int USE_ENVIRONMENTAL_VARIABLES = 1;

    static String URL;
    static String CONNECTION_USERNAME;
    static String CONNECTION_PASSWORD;

    static Connection getConnection() {
        Connection connection = null;

        try {
            setFields(USE_ENVIRONMENTAL_VARIABLES);
            connection = DriverManager.getConnection(
                    URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    static void setFields() throws IOException {
        // create a stream from properties file so we can read from it
        FileInputStream fileStream = new FileInputStream(
                "application.properties"); 

        // create a Properties object and get information from it
        Properties properties = new Properties(); 
        properties.load(fileStream);

        // extract values from the keys into variables
        URL = properties.getProperty("URL");	
        CONNECTION_USERNAME = properties.getProperty("CONNECTION_USERNAME"); 
        CONNECTION_PASSWORD = properties.getProperty("CONNECTION_PASSWORD"); 
    }

    static void setFields(int useEnvironmentalVariables) throws IOException {
        // create a stream from properties file so we can read from it
        FileInputStream fileStream = new FileInputStream(
                "application.properties"); 

        // create a Properties object and get information from it
        Properties properties = new Properties(); 
        properties.load(fileStream);

        // extract values from keys (the values are the names of our environment 
        // variables)
        String url = properties.getProperty("URL_ENV");
        String username = properties.getProperty("CONNECTION_USERNAME_ENV");
        String password = properties.getProperty("CONNECTION_PASSWORD_ENV");

        System.out.println(url);
        System.out.println(username);
        System.out.println(password);

        // now that we have our environment variable names, let's use those to 
        // get their associated values, the actual credentials!
        URL = System.getenv(url);
        CONNECTION_USERNAME = System.getenv(username);
        CONNECTION_PASSWORD = System.getenv(password);

        System.out.println(URL);
        System.out.println(CONNECTION_USERNAME);
        System.out.println(CONNECTION_PASSWORD);
    } 
}
