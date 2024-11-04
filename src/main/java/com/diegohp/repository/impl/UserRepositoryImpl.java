package com.diegohp.repository.impl;

import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager
                .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);

        try {
            User user = query.getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }



    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
