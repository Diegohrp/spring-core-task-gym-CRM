package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.entity.training.Training;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import com.diegohp.service.TraineeService;
import com.diegohp.storage.TraineeStorage;
import com.diegohp.storage.TrainerStorage;
import com.diegohp.storage.TrainingStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TraineeStorage traineeStorage = context.getBean(TraineeStorage.class);
        for (Trainee t : traineeStorage.getStorage().values()) {
            logger.debug(t.toString());
        }
        System.out.println("-------------------------------------------------------------------------------");
        TrainerStorage trainerStorage = context.getBean(TrainerStorage.class);
        for (Trainer t : trainerStorage.getStorage().values()) {
            logger.debug(t.toString());
        }
        System.out.println("-------------------------------------------------------------------------------");
        TrainingStorage trainingStorage = context.getBean(TrainingStorage.class);
        for (Training t : trainingStorage.getStorage().values()) {
            logger.debug(t.toString());
        }
    }
}
