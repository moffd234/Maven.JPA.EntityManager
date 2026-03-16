package com.zipcode.jpaentity.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ComputerService {
    private final EntityManager manager;

    public ComputerService(EntityManager manager) {
        this.manager = manager;
    }

    private void rollback(EntityTransaction transaction, RuntimeException e) throws RuntimeException {
        if (transaction.isActive()) {
            transaction.rollback();
        }

        throw e;
    }
}
