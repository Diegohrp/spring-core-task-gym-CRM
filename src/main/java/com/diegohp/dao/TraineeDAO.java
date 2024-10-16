package com.diegohp.dao;

import com.diegohp.entity.user.Trainee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TraineeDAO {
    private Map<Long, Trainee> traineeStorage = new HashMap<>();

    public void create(Trainee trainee) {
        this.traineeStorage.put(trainee.getId(), trainee);
    }

    public void update(Trainee trainee) {
        this.traineeStorage.put(trainee.getId(), trainee);
    }

    public Trainee getById(Long id) {
        return this.traineeStorage.get(id);
    }

    public void delete(Long id) {
        this.traineeStorage.remove(id);
    }
}