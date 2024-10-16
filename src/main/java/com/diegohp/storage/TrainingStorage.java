package com.diegohp.storage;

import com.diegohp.entity.training.Training;

import java.util.HashMap;
import java.util.Map;

public class TrainingStorage {
    private final Map<String, Training> storage = new HashMap<>();

    public Map<String, Training> getStorage() {
        return storage;
    }
}
