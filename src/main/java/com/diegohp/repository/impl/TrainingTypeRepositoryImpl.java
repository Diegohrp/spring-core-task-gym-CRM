package com.diegohp.repository.impl;

import com.diegohp.entity.training.TrainingType;
import com.diegohp.repository.interfaces.TrainingTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TrainingTypeRepositoryImpl implements TrainingTypeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(TrainingType trainingType) {
        entityManager.persist(trainingType);
    }

    @Override
    public Optional<TrainingType> getById(Long id) {
        TrainingType trainingType = entityManager.find(TrainingType.class, id);
        return trainingType != null ? Optional.of(trainingType) : Optional.empty();
    }
}
