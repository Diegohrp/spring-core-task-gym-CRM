package com.diegohp.config.storage;

import com.diegohp.storage.TraineeStorage;
import com.diegohp.storage.TrainerStorage;
import com.diegohp.storage.TrainingStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Bean
    public TraineeStorage traineeStorage() {
        return new TraineeStorage();
    }

    @Bean
    public TrainerStorage trainerStorage() {
        return new TrainerStorage();
    }

    @Bean
    public TrainingStorage trainingStorage() {
        return new TrainingStorage();
    }

}
