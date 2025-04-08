package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation of the {@link NoteDao} interface. Provides methods for
 * interacting with a database to perform operations related to {@link Note}
 * objects, including adding new notes and retrieving notes by their ID.
 */
public class NoteDaoImpl implements NoteDao {
    // write
    /**
     * Adds a new note to the database.
     *
     * This method opens a connection to the database, prepares an SQL `INSERT`
     * statement to add a new note with the provided content and priority,
     * executes the statement, and returns a {@link Note} object with the
     * generated ID of the newly added note.
     *
     * @param id the ID of the note to be added (though it's not used directly,
     *           the generated ID from the DB
     *           will be assigned to the note).
     * @param content the content of the note to be added.
     * @param priority the priority of the note to be added.
     * @return a {@link Note} object with the generated ID, content, and
     *         priority if the operation is successful;
     *         null if the operation fails.
     */
    public Note addNote(long id, String content, String priority) {

        // Steps 1 & 5: Open connection to db and close when done.
        try (Connection connection =
            ConnectionFactory.getConnectionFactory().getConnection()) {

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
            // While there is another record in the resultset to process...
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
    /**
     * Retrieves a note from the database by its ID.
     *
     * This method opens a connection to the database, prepares an SQL `SELECT`
     * statement to fetch a note by its ID, executes the statement, and returns
     * a {@link Note} object with the retrieved values.
     *
     * @param id the ID of the note to be retrieved.
     * @return a {@link Note} object with the ID, content, and priority if the
     *         operation is successful;
     *         null if the operation fails or no note is found with the given
     *         ID.
     */
    public  Note getNoteById(long id) {

        // Step 1 & 5: Open a connection to the db and close it when done.
        try (Connection connection =
            ConnectionFactory.getConnectionFactory().getConnection()) {

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
