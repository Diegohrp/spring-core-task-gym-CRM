package com.diegohp.dao;

import com.diegohp.entity.user.Trainer;
import com.diegohp.storage.TrainerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainerDAO {
    /*private Map<Long, Trainer> trainerStorage = new HashMap<>();

    @Autowired
    public TrainerDAO(TrainerStorage trainerStorage) {
        this.trainerStorage = trainerStorage.getStorage();
    }

    public void create(Trainer trainer) {
        trainerStorage.put(trainer.getId(), trainer);
    }

    public void update(Long id, Trainer trainer) {
        trainerStorage.put(id, trainer);
    }

    public Trainer findById(Long id) {
        return trainerStorage.get(id);
    }

    public Trainer findByUsername(String username) {
        for (Trainer trainer : trainerStorage.values()) {
            if (trainer.getUsername().equals(username)) {
                return trainer;
            }
        }

        return null;
    }

    public List<Trainer> getAll() {
        return trainerStorage.values().stream().toList();
    }

     */
}
