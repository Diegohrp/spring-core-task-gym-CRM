package com.diegohp.storage;

import com.diegohp.entity.user.Trainer;

import java.util.HashMap;
import java.util.Map;

public class TrainerStorage {
    private final Map<Long, Trainer> storage = new HashMap<>();

    public Map<Long, Trainer> getStorage() {
        return storage;
    }
}
