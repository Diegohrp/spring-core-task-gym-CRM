package com.diegohp.storage;

import com.diegohp.entity.user.Trainee;

import java.util.HashMap;
import java.util.Map;

public class TraineeStorage {
    private final Map<Long, Trainee> storage = new HashMap<>();

    public Map<Long, Trainee> getStorage() {
        return storage;
    }
}
