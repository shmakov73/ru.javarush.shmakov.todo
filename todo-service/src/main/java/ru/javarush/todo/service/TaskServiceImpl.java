package ru.javarush.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.todo.dao.TaskDao;
import ru.javarush.todo.dao.TaskHibernateDao;
import ru.javarush.todo.entity.Status;
import ru.javarush.todo.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAll(int offset, int limit, String user){
        return taskDao.getAll(offset, limit, user);
    }

    public int getAllCountByUser(String user){
        return taskDao.getAllCountByUser(user);
    }

    @Transactional
    public Task edit(int id, String description, Status status){
        Task task = taskDao.getById(id);
        if (isNull(task)){
            logger.warn("Task: {} not found", task);
            throw new RuntimeException("Not found");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDao.saveOrUpdate(task);
        logger.info("Task :{} edited", task);
        return task;
    }

    public Task create(String description, Status status, String user){
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        task.setTime(LocalDateTime.now());
        task.setUser(user);
        taskDao.saveOrUpdate(task);
        logger.info("Added new Task: {}", task);
        return task;
    }

    @Transactional
    public void delete(int id){
        Task task = taskDao.getById(id);
        if (isNull(task)){
            logger.warn("Task :{} not found", task);
            throw new RuntimeException("Not found");
        }
        taskDao.delete(task);
        logger.info("Task :{} deleted", task);
    }
}