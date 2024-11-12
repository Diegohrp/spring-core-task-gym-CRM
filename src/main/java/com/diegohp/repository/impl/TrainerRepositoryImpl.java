package com.diegohp.repository.impl;

import com.diegohp.entity.user.Trainer;
import com.diegohp.repository.interfaces.TrainerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrainerRepositoryImpl implements TrainerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Trainer trainee) {
        this.entityManager.persist(trainee);
    }

    @Override
    public Optional<Trainer> getById(Long id) {
        Trainer trainer = this.entityManager.find(Trainer.class, id);
        return trainer != null ? Optional.of(trainer) : Optional.empty();
    }

    @Override
    public Optional<Trainer> getByUsername(String username) {
        TypedQuery<Trainer> query = entityManager.createQuery(
                "SELECT t FROM Trainer t INNER JOIN t.user u WHERE u.username = :username", Trainer.class);
        query.setParameter("username", username);
        try {
            Trainer trainer = query.getSingleResult();
            return Optional.of(trainer);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Trainer trainer) {
        this.entityManager.merge(trainer);
    }

    @Override
    public Optional<List<Trainer>> getUnassigned(String trainee) {
        TypedQuery<Trainer> query = entityManager.createQuery(
                "SELECT t FROM Trainer t WHERE t NOT IN " +
                        "(SELECT t2 FROM Trainer  t2 INNER JOIN t2.trainings trainings WHERE trainings.trainee.user.username =: trainee)",
                Trainer.class);
        query.setParameter("trainee", trainee);
        return Optional.of(query.getResultList());
    }
}
