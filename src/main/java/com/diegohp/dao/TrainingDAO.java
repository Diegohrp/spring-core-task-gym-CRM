package com.diegohp.dao;

import com.diegohp.entity.training.Training;
import com.diegohp.storage.TrainingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrainingDAO {
    private Map<String, Training> trainingStorage = new HashMap<>();

    @Autowired
    public TrainingDAO(TrainingStorage trainingStorage) {
        this.trainingStorage = trainingStorage.getStorage();
    }

    public void create(Training training) {
        /*String id = training.getTrainerId().toString() + "-" + training.getTraineeId().toString();
        this.trainingStorage.put(id, training);*/
    }

    public Training findById(String id) {
        return this.trainingStorage.get(id);
    }

    public List<Training> getAll() {
        return trainingStorage.values().stream().toList();
    }
}
