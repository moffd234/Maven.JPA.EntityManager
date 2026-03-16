package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserService {
    private final EntityManager entityManager;

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        String query = "SELECT u FROM User u"; // Select <alias> from <entityClass (not table name) <alias>
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);

        return typedQuery.getResultList();
    }


    public void create(User user) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (RuntimeException e) {
            rollback(transaction, e);
        }
    }

    public void update(User user) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(user);
            transaction.commit();
        } catch (RuntimeException e) {
            rollback(transaction, e);
        }
    }

    public void delete(int id) {
        User user = findById(id);

        if (user != null) {
            EntityTransaction transaction = entityManager.getTransaction();

            try {
                transaction.begin();
                entityManager.remove(user);
                transaction.commit();
            } catch (RuntimeException e) {
                rollback(transaction, e);
            }
        }
    }

    private void rollback(EntityTransaction transaction, RuntimeException e) throws RuntimeException {
        if (transaction.isActive()) {
            transaction.rollback();
        }

        throw e;
    }
}
