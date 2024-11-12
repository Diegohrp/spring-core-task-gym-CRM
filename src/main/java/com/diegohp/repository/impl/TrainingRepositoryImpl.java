package com.diegohp.repository.impl;

import com.diegohp.entity.training.Training;
import com.diegohp.repository.interfaces.TrainingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Training training) {
        this.entityManager.persist(training);
    }
}
