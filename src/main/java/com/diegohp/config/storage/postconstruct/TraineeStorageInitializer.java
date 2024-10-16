package com.diegohp.config.storage.postconstruct;

import com.diegohp.entity.user.Trainee;
import com.diegohp.storage.TraineeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class TraineeStorageInitializer extends StorageInitializer<Long, Trainee> {

    @Autowired
    public TraineeStorageInitializer(
            @Value("${trainee.data.path}") Resource traineeDataFile,
            TraineeStorage traineeStorage) {
        super(traineeDataFile, traineeStorage.getStorage(), Trainee.class);
    }

    @Override
    protected Long getId(Trainee trainee) {
        return trainee.getId();
    }
}