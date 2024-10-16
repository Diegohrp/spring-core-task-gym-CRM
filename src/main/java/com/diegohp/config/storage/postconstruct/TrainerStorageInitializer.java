package com.diegohp.config.storage.postconstruct;

import com.diegohp.entity.user.Trainer;
import com.diegohp.storage.TrainerStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class TrainerStorageInitializer extends StorageInitializer<Long, Trainer> {
    public TrainerStorageInitializer(
            @Value("${trainer.data.path}") Resource dataFile,
            TrainerStorage trainerStorage) {
        super(dataFile, trainerStorage.getStorage(), Trainer.class);
    }

    @Override
    protected Long getId(Trainer trainer) {
        return trainer.getId();
    }
}
