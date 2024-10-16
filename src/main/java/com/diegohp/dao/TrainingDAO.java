package com.diegohp.dao;

import com.diegohp.entity.training.Training;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TrainingDAO {
    private Map<String, Training> trainingStorage = new HashMap<>();

    public void create(Training training) {
        String id = Long.toString(training.getTrainerId()) + Long.toString(training.getTraineeId());
        this.trainingStorage.put(id, training);
    }

    public Training getById(String id) {
        return this.trainingStorage.get(id);
    }
}
