package com.diegohp.service;

import com.diegohp.dto.training.CreateTrainingDto;
import com.diegohp.entity.training.Training;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import com.diegohp.repository.interfaces.TrainingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class TrainingService {
    private final TrainingRepository repository;
    private final TraineeService traineeService;
    private final TrainerService trainerService;

    private Logger logger;

    @Autowired
    public TrainingService(TrainingRepository repository, TraineeService traineeService, TrainerService trainerService) {
        this.repository = repository;
        this.traineeService = traineeService;
        this.trainerService = trainerService;

    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Transactional
    public void create(CreateTrainingDto trainingDto) {
        logger.info("------------------------------- Training Creation -----------------------------------------");
        try {
            Trainee trainee = traineeService.getById(trainingDto.getTraineeId()).get();
            Trainer trainer = trainerService.getById(trainingDto.getTrainerId()).get();
            Training training = new Training(trainee, trainer, trainingDto.getName(), trainer.getSpeciality(), trainingDto.getDate(), trainingDto.getDuration());

            repository.create(training);
            logger.info("New Training created: {}", training);
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Optional<List<Training>> getTraineeTrainings(String trainee, Date fromDate, Date toDate, String trainerName, Long type) {
        Optional<List<Training>> trainings = repository.getByCriteria(trainee, fromDate, toDate, trainerName, type);
        logger.info("------------------------------------------------- Trainee Trainings By Criteria --------------------------------------------------");
        if (trainings.isPresent()) {
            for (Training t : trainings.get()) {
                logger.info(t.toString());
            }
        }
        return trainings;
    }

    public Optional<List<Training>> getTrainerTrainings(String trainer, Date fromDate, Date toDate, String traineeName) {
        Optional<List<Training>> trainings = repository.getByCriteria(trainer, fromDate, toDate, traineeName);
        logger.info("------------------------------------------------- Trainer Trainings By Criteria --------------------------------------------------");
        if (trainings.isPresent()) {
            for (Training t : trainings.get()) {
                logger.info(t.toString());
            }
        }
        return trainings;
    }
}
