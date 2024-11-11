package com.diegohp.repository.interfaces;

import com.diegohp.entity.training.TrainingType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Optional;


public interface TrainingTypeRepository {
    void create(TrainingType trainingType);

    Optional<TrainingType> getById(Long id);

}
