package ru.javarush.todo.dao;

//import ru.javarush.todo.entity.Task;

import ru.javarush.todo.entity.Task;

import java.util.List;

public interface TaskDao {
    List<Task> getAll(int offset, int limit, String user);

    int getAllCountByUser(String user);

    Task getById(int id);

    void saveOrUpdate(Task task);

    void delete(Task task);
}