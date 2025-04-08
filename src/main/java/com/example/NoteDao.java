package com.example;

public interface NoteDao {
    public Note addNote(long id, String content, String priority);
    public  Note getNoteById(long id);
}
