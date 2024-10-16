package com.diegohp.dao;

import com.diegohp.entity.user.Trainer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TrainerDAO {
    private Map<Long, Trainer> trainerStorage = new HashMap<>();

    private void create(Trainer trainer) {
        this.trainerStorage.put(trainer.getId(), trainer);
    }

    private void update(Trainer trainer) {
        this.trainerStorage.put(trainer.getId(), trainer);
    }

    private Trainer getById(Long id) {
        return this.trainerStorage.get(id);
    }
}
