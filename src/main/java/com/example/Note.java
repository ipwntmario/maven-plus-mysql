package com.example;

import java.util.ArrayList;

/**
 * Represents a Note with an ID, content, and priority.
 * <p>
 * The {@code Note} class is used to store and manage information about a single note
 * in a note-taking application, where each note has a unique ID, associated content,
 * and a priority level.
 * </p>
 */
public class Note {
    private long id;
    private String content;
    private String priority;

    /**
     * Constructs a {@code Note} with the specified ID, content, and priority.
     *
     * @param id the unique identifier for the note.
     * @param content the text content of the note.
     * @param priority the priority level of the note (e.g., high, medium, low).
     */
    public Note(long id, String content, String priority) {
        this.id = id;
        this.content = content;
        this.priority = priority;
    }

    /**
     * Default constructor that creates an empty {@code Note} with default
     * values.
     * <p>
     * The default values are:
     * <ul>
     *   <li>ID = 0</li>
     *   <li>Content = null</li>
     *   <li>Priority = null</li>
     * </ul>
     * </p>
     */
    public Note() {
        this.id = 0;
        this.content = null;
        this.priority = null;
    }

    /**
     * Retrieves all the properties of the note as an {@link ArrayList}.
     * <p>
     * This method returns the ID, content, and priority of the note in an {@link ArrayList<Object>}
     * in the order: ID, content, and priority.
     * </p>
     *
     * @return an {@link ArrayList} containing the ID, content, and priority of the note.
     */
    public ArrayList<Object> getAll() {
        ArrayList<Object> all = new ArrayList<Object>();
        all.add(id);
        all.add(content);
        all.add(priority);
        return all;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
