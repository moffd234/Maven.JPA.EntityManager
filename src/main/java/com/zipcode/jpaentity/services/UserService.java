package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserService implements ServiceInterface<User>{
    private final EntityManager manager;

    public UserService(EntityManager entityManager) {
        this.manager = entityManager;
    }

    public User findById(int id) {
        return manager.find(User.class, id);
    }

    public List<User> findAll() {
        String query = "SELECT u FROM User u"; // Select <alias> from <entityClass (not table name) <alias>
        TypedQuery<User> typedQuery = manager.createQuery(query, User.class);

        return typedQuery.getResultList();
    }


    public void create(User user) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
        } catch (RuntimeException e) {
            rollback(transaction, e);
        }
    }

    public void update(User user) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.merge(user);
            transaction.commit();
        } catch (RuntimeException e) {
            rollback(transaction, e);
        }
    }

    public void delete(int id) {
        User user = findById(id);

        if (user != null) {
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();
                manager.remove(user);
                transaction.commit();
            } catch (RuntimeException e) {
                rollback(transaction, e);
            }
        }
    }
}
