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

import java.util.List;


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

    /*
    private final TrainingDAO trainingDAO;
    private final TraineeDAO traineeDAO;
    private final TrainerDAO trainerDAO;
    private Logger logger;

    @Autowired
    public TrainingService(TrainingDAO trainingDAO, TraineeDAO traineeDAO, TrainerDAO trainerDAO) {
        this.trainingDAO = trainingDAO;
        this.traineeDAO = traineeDAO;
        this.trainerDAO = trainerDAO;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void create(Training data) {
        logger.info("-------------------------------Training Creation-----------------------------------------");
        Training training = new Training(data);
        Trainer trainer = trainerDAO.findById(training.getTrainerId());

        if (trainer == null) {
            logger.error("Trainer does not exist");
            return;
        }

        if (traineeDAO.findById(training.getTraineeId()) == null) {
            logger.error("Trainee does not exist");
            return;
        }
        String id = training.getTrainerId().toString() + "-" + training.getTraineeId().toString();

        if (trainingDAO.findById(id) != null) {
            logger.error("This training already exists");
            return;
        }

        if (trainer.getSpeciality() != training.getType()) {
            logger.error("Training type does not match trainer speciality");
            return;
        }

        trainingDAO.create(training);
        logger.info("New Training created: {}", training);
    }

    public Training get(String id) {
        logger.info("-------------------------------Select Training-----------------------------------------");
        Training training = trainingDAO.findById(id);
        if (training != null) {
            logger.info("You selected Training: {}", training);
            return training;
        } else {
            logger.warn("Training with ID: {} not found", id);
        }

        return null;
    }

    public List<Training> getAll() {
        logger.info("-------------------------------Select All Trainings-----------------------------------------");
        List<Training> trainings = trainingDAO.getAll();

        for (Training training : trainings) {
            logger.info("In Storage: {}", training);
        }

        return trainings;
    }
*/
}
