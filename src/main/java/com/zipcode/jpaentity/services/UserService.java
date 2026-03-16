package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.User;
import jakarta.persistence.EntityManager;
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
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public void delete(int id) {
        User user = findById(id);

        if(user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
    }
}
