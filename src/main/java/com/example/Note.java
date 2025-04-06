package com.example;

import java.util.ArrayList;

public class Note {
    private long id;
    private String content;
    private String priority;

    public Note(long id, String content, String priority) {
        this.id = id;
        this.content = content;
        this.priority = priority;
    }

    public Note() {
        this.id = 0;
        this.content = null;
        this.priority = null;
    }

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
