package com.diegohp.dao;

import com.diegohp.entity.user.Trainee;
import com.diegohp.storage.TraineeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TraineeDAO {
   /* private final Map<Long, Trainee> traineeStorage;

    @Autowired
    public TraineeDAO(TraineeStorage storage) {
        this.traineeStorage = storage.getStorage();
    }


    public void create(Trainee trainee) {
        this.traineeStorage.put(trainee.getId(), trainee);
    }

    public void update(Long id, Trainee trainee) {
        this.traineeStorage.put(id, trainee);
    }

    public Trainee findById(Long id) {
        return this.traineeStorage.get(id);
    }

    public Trainee findByUsername(String username) {
        for (Trainee trainee : traineeStorage.values()) {
            if (trainee.getUsername().equals(username)) {
                return trainee;
            }
        }
        return null;
    }

    public void delete(Long id) {
        this.traineeStorage.remove(id);
    }

    public List<Trainee> getAll() {
        return this.traineeStorage.values().stream().toList();
    }

    */
}