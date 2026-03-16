package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.Computer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ComputerService implements ServiceInterface<Computer> {
    private final EntityManager manager;

    public ComputerService(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Computer findById(int id) {
        return manager.find(Computer.class, id);
    }

    @Override
    public List<Computer> findAll() {
        String query = "SELECT c FROM Computer c";
        return manager.createQuery(query, Computer.class).getResultList();
    }

    @Override
    public void create(Computer computer) {
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.persist(computer);
            transaction.commit();
        } catch (RuntimeException e) {
            rollback(transaction, e);
        }
    }

    @Override
    public void update(Computer input) {

        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            manager.merge(input);
            transaction.commit();

        } catch (RuntimeException e) {
            rollback(transaction, e);
        }

    }

    @Override
    public void delete(int id) {

    }
}
