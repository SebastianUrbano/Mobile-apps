package com.example.basededatos.model.entity;

public class Task {

    private String id;
    private String name;
    private String Description;
    private boolean complete;

    public Task(String id, String name, String description, boolean complete) {
        this.id = id;
        this.name = name;
        Description = description;
        this.complete = complete;
    }

    public Task() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
