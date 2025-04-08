package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionFactory provides a simple, reusable way of  connecting to our SQL
 * database.
 */
public final class ConnectionFactory {

    static final int USE_ENVIRONMENTAL_VARIABLES = 1;

    /**
     * There are two class variables included within our ConnectionFactory
     * utility class. First, is the single connectionFactory object itself
     * This will be passed along to any layer that will make requests to
     * the database. Second, is the props object of the Properties Class that
     * will allow us the ability to access our db.properties to obtain our
     * sensitive information.
     */
    private static final ConnectionFactory CONNECTION_FACTORY =
        new ConnectionFactory();
    private Properties props = new Properties();

    /**
     * We include a private constructor here to make sure that there are
     * no other possibilities to create another instance of our
     * ConnectionFactory object. Along with this, during the construction
     * of our connectionFactory object we make sure we can load in
     * our db.properties file, handling any potential exception thrown.
     */
    private ConnectionFactory() {
        try {
            props.load(
                new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory(int useEnvironmentalVariables) {
        if (useEnvironmentalVariables == 1) {
            try {
                props.setProperty(
                    "url",
                    System.getenv("url"));
                props.setProperty(
                    "username",
                    System.getenv("connectionUsername"));
                props.setProperty(
                    "password",
                    System.getenv("connectionPassword"));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Next, we must also include a static method to obtain the single
     * instance of our connectionFactory, to allow this to be accessible
     * within other classes. This way we can provide the connectionFactory
     * which can subsequently invoke the getConnection() method when we
     * need to make requests of our database.
     */
    public static ConnectionFactory getConnectionFactory() {
        return CONNECTION_FACTORY;
    }

    /**
     * This method provides the ability for classes to utilize the
     * getConnection() method from our Utility class and establish a
     * connection with our database that can be used to execute SQL
     * statements through the Statement or PreparedStatement interfaces.
     * This will also check for any SQLException, incase the information
     * provided in the db.properties is incorrect.
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
