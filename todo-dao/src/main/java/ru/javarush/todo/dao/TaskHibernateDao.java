package ru.javarush.todo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.todo.entity.Task;

import java.util.List;

@Repository
public class TaskHibernateDao implements TaskDao {

    private final SessionFactory sessionFactory;

    public TaskHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Task> getAll(int offset, int limit, String user){
        Query<Task> query = getSession().createQuery("select t from Task t where t.user = :USER", Task.class);
        query.setParameter("USER", user);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int getAllCountByUser(String user){
        Query<Long> query = getSession().createQuery("select count(t) from Task t where t.user = :USER", Long.class);
        query.setParameter("USER", user);
        return Math.toIntExact(query.uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Task getById(int id){
        Query<Task> query = getSession().createQuery("select t from Task t where t.id = :ID", Task.class);
        query.setParameter("ID", id);
        return query.uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(Task task){
        getSession().persist(task);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Task task){
        getSession().remove(task);
    }

    @Transactional
    public void findOrCreateUser(String name){
        Query<Task> query = getSession().createQuery("select t from Task t where t.user = :NAME", Task.class);
        query.setParameter("NAME", name);
        Task task = query.uniqueResult();
        if (task == null){

        }
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}