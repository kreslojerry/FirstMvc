package org.firstmvc.dao;

import org.firstmvc.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        entityManager.remove(
                entityManager.find(User.class, id)
        );
    }

    @Override
    @Transactional
    public void editUser(int id, User updateUser) {
        entityManager.merge(
                entityManager.find(User.class, id)
                        .copyWithoutId(updateUser)
        );
    }

    @Override
    public User getUserById(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id",
                User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.email = :email",
                User.class);
        query.setParameter("email", email);
        return query.getResultList().stream().findAny().orElse(null);
    }
}
