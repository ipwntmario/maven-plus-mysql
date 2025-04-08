package com.example;

import java.sql.*;

public class App {
    ConnectionFactory connectionFactory = 
        ConnectionFactory.getConnectionFactory();

    public static void main(String[] args) {
        App app = new App();
        Note testNote = app.getNoteById(2);
        System.out.println("ID: " + testNote.getId());
        System.out.println("Content: " + testNote.getContent());
        System.out.println("Priority: " + testNote.getPriority());
    }

    // write
    public Note addNote(long id, String content, String priority) {

        // Steps 1 & 5: Open connection to db and close when done.
        try (Connection connection = connectionFactory.getConnection()){

            // Step 2: Create your statement.
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO notes (content, priority) VALUES (?, ?)", 
                    Statement.RETURN_GENERATED_KEYS);

            // Assign any parameters to their values.
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, priority);

            // Step 3: Execute the statement.
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // Step 4: Process the results.
            // While there is another record in the resultset to rpocess...
            while (resultSet.next()) {
                // ...get the value of the furst column in that resultset row...
                long resultId = resultSet.getLong(1);

                // ... and return a Note with the generated id in its state, as 
                // well as the other values.
                return new Note(resultId, content, priority);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null if unsuccessful.
        return null;
    }

    // read
    public  Note getNoteById(long id) {

        // Step 1 & 5: Open a connection to the db and close it when done.
        try (Connection connection = connectionFactory.getConnection()) {

            // Step 2: Create your statement.
            PreparedStatement preparedStatement = 
                connection.prepareStatement(
                    "SELECT * FROM notes WHERE id = ?");

            // Assign any parameters their values.
            preparedStatement.setLong(1, id);

            // Step 3: Execute the statement.
            ResultSet resultSet = preparedStatement.executeQuery();

            // Step 4: Process the results.
            // while there is another record in the resultset to process...
            while (resultSet.next()) {
                // ... get the values from the respective columns...
                long resultId = resultSet.getLong("id");
                String content = resultSet.getString("content");
                String priority = resultSet.getString("priority");

                // ...and return a Note with those values as its state.
                return new Note(resultId, content, priority);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Return null if unsuccessful.
        return null;
    }
}
