package com.zipcode.jpaentity.services;

import jakarta.persistence.EntityTransaction;

import java.util.List;

public interface ServiceInterface<T> {
    public T findById(int id);
    public List<T> findAll();
    public void create(T input);
    public void update(T input);
    public void delete(int id);

    public default void rollback(EntityTransaction transaction, RuntimeException e) throws RuntimeException {
        if (transaction.isActive()) {
            transaction.rollback();
        }

        throw e;
    }
}
