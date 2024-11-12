package com.diegohp.config.storage.data;

import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.training.CreateTrainingDto;
import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.service.TraineeService;
import com.diegohp.service.TrainerService;
import com.diegohp.service.TrainingService;
import com.diegohp.service.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StorageInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final TrainingTypeService trainingTypeService;
    private final TrainingTypes[] trainingTypes;

    private final List<CreateTraineeDto> traineeDtos;
    private final TraineeService traineeService;

    private final List<CreateTrainerDto> trainerDtos;
    private final TrainerService trainerService;

    private final List<CreateTrainingDto> trainingDtos;
    private final TrainingService trainingService;

    @Autowired
    public StorageInitializer(TrainingTypeService trainingTypeService,
                              TrainingTypes[] trainingTypes,
                              TraineeService traineeService,
                              List<CreateTraineeDto> traineeDtos,
                              TrainerService trainerService,
                              List<CreateTrainerDto> trainerDtos,
                              TrainingService trainingService,
                              List<CreateTrainingDto> trainingDtos) {
        this.trainingTypeService = trainingTypeService;
        this.trainingTypes = trainingTypes;
        this.traineeService = traineeService;
        this.traineeDtos = traineeDtos;
        this.trainerService = trainerService;
        this.trainerDtos = trainerDtos;
        this.trainingService = trainingService;
        this.trainingDtos = trainingDtos;
    }


    private void insertTrainingTypes() {
        for (TrainingTypes t : trainingTypes) {
            trainingTypeService.create(new TrainingType(t.getName()));
        }
    }

    private void insertTrainees() {
        traineeDtos.forEach(traineeService::create);
    }

    private void insertTrainers() {
        trainerDtos.forEach(trainerService::create);
    }

    private void insertTrainings() {
        trainingDtos.forEach(trainingService::create);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        insertTrainingTypes();
        insertTrainees();
        insertTrainers();
        insertTrainings();
    }
}
