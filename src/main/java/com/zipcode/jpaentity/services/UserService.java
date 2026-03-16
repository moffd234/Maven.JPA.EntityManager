package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.User;
import jakarta.persistence.EntityManager;

public class UserService {
    private final EntityManager entityManager;

    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User findById(int id){
        return entityManager.find(User.class, id);
    }


}
