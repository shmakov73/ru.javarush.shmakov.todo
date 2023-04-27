package ru.javarush.todo.controller;

import ru.javarush.todo.entity.Status;


public class TaskInfo {

    private String description;

    private Status status;

    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }
}