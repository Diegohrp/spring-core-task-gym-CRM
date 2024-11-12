package com.diegohp.repository.interfaces;

import com.diegohp.entity.user.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerRepository {
    void create(Trainer trainee);

    Optional<Trainer> getById(Long id);

    Optional<Trainer> getByUsername(String username);

    void update(Trainer trainer);

    Optional<List<Trainer>> getUnassigned(String trainee);
}
