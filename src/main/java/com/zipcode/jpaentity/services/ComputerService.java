package com.zipcode.jpaentity.services;

import com.zipcode.jpaentity.entities.Computer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ComputerService implements ServiceInterface<Computer>{
    private final EntityManager manager;

    public ComputerService(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Computer findById(int id) {
        return null;
    }

    @Override
    public List<Computer> findAll() {
        return List.of();
    }

    @Override
    public void create(Computer input) {

    }

    @Override
    public void update(Computer input) {

    }

    @Override
    public void delete(int id) {

    }
}
