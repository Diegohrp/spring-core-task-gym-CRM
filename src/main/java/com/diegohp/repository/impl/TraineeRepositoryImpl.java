package com.diegohp.repository.impl;

import com.diegohp.entity.user.Trainee;
import com.diegohp.repository.interfaces.TraineeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void create(Trainee trainee) {
        entityManager.persist(trainee);
    }

    @Override
    public Optional<Trainee> getById(Long id) {
        Trainee trainee = entityManager.find(Trainee.class, id);
        return trainee != null ? Optional.of(trainee) : Optional.empty();
    }

    @Override
    public Optional<Trainee> getByUsername(String username) {
        TypedQuery<Trainee> query = entityManager.createQuery(
                "SELECT t FROM Trainee t INNER JOIN t.user u WHERE u.username = :username", Trainee.class);
        query.setParameter("username", username);
        try {
            Trainee trainee = query.getSingleResult();
            return Optional.of(trainee);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Trainee trainee) {
        entityManager.merge(trainee);
    }

    @Override
    public void delete(Trainee trainee) {
        entityManager.remove(trainee);
    }
}
