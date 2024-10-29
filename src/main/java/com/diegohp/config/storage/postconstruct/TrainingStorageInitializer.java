package com.diegohp.config.storage.postconstruct;

import com.diegohp.entity.training.Training;
import com.diegohp.storage.TrainingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class TrainingStorageInitializer extends StorageInitializer<String, Training> {
    @Autowired
    public TrainingStorageInitializer(
            @Value("${training.data.path}") Resource dataFile,
            TrainingStorage trainingStorage) {
        super(dataFile, trainingStorage.getStorage(), Training.class);
    }

    @Override
    protected String getId(Training training) {
        return ""; //training.getTrainerId() + "-" + training.getTraineeId();
    }
}
