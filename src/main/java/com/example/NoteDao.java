package com.example;

import java.util.List;

/**
 * The {@code NoteDao} interface defines the data access methods for managing
 * {@link Note} objects. It provides methods for adding new notes and retrieving
 * notes by their unique identifier.
 * <p>
 * Implementations of this interface are expected to provide concrete behavior
 * for interacting with a data storage (e.g., a database or in-memory storage)
 * to perform these operations.
 * </p>
 */
public interface NoteDao {
    int addNote(String content, String priority);

    Note getNoteById(long id);

    List<Note> getAllNotesTruncated();
}
