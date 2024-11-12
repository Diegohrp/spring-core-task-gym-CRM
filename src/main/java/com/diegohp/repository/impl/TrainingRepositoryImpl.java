package com.diegohp.repository.impl;

import com.diegohp.entity.training.Training;
import com.diegohp.repository.interfaces.TrainingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Training training) {
        this.entityManager.persist(training);
    }

    @Override
    public Optional<List<Training>> getByCriteria(String trainee, Date fromDate, Date toDate, String trainerName, Long type) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainee.user.username = :trainee AND t.date BETWEEN :fromDate AND :toDate AND t.type.id = :type AND t.trainer.user.firstName =: trainerName",
                Training.class);
        query.setParameter("trainee", trainee);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("type", type);
        query.setParameter("trainerName", trainerName);

        return Optional.of(query.getResultList());
    }

    @Override
    public Optional<List<Training>> getByCriteria(String trainer, Date fromDate, Date toDate, String traineeName) {
        TypedQuery<Training> query = entityManager.createQuery(
                "SELECT t FROM Training t WHERE t.trainer.user.username = :trainer AND t.date BETWEEN :fromDate AND :toDate AND t.trainee.user.firstName =: traineeName",
                Training.class);
        query.setParameter("trainer", trainer);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("traineeName", traineeName);

        return Optional.of(query.getResultList());
    }
}
