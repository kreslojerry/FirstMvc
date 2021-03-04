package org.firstmvc.dao;

import org.firstmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class HibernateUserDAO implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getUserList() {
        return currentSession().createQuery("from User order by id", User.class).list();
    }

    @Override
    public void addUser(User user) {
        currentSession().save(user);
    }

    @Override
    public void deleteUser(int id) {
        Session session = currentSession();
        session.beginTransaction();
        session.delete(getUserById(id));
        session.getTransaction().commit();
    }

    @Override
    public void editUser(int id, User updateUser) {
        Session session = currentSession();
        session.beginTransaction();
        session.update(
                getUserById(id).copyWithoutId(updateUser)
        );
        session.getTransaction().commit();
    }

    @Override
    public User getUserById(int id) {
        Query<User> query = currentSession().createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);
        return query.list().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        Query<User> query = currentSession().createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return query.list().stream().findAny().orElse(null);
    }
}
