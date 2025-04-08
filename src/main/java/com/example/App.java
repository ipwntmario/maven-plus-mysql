package com.example;

/**
 * App is an app that can insert and query from an SQL table of notes.
 */
public final class App {
    // Private constructor to prevent instantiation
    private App() {
        throw new UnsupportedOperationException(
            "Utility class should not be instantiated.");
    }

    /**
     * Start of the program.
     * @param args does nothing.
     */
    public static void main(String[] args) {
        // App app = new App();
        NoteDao noteDao = new NoteDaoImpl();
        Note testNote = noteDao.getNoteById(2);
        System.out.println("ID: " + testNote.getId());
        System.out.println("Content: " + testNote.getContent());
        System.out.println("Priority: " + testNote.getPriority());
    }
}
