package com.diegohp.service;

import com.diegohp.dao.TraineeDAO;
import com.diegohp.dao.TrainerDAO;
import com.diegohp.dao.TrainingDAO;
import com.diegohp.entity.training.Training;
import com.diegohp.storage.TrainingStorage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TrainingService {
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
        Training training = new Training(data);
        if (trainerDAO.findById(training.getTrainerId()) == null) {
            logger.error("Trainer does not exist");
            return;
        }

        if (traineeDAO.findById(training.getTraineeId()) == null) {
            logger.error("Trainee does not exist");
            return;
        }
        String id = training.getTrainerId().toString() + "-" + training.getTraineeId().toString();

        if (trainingDAO.findById(id) != null) {
            logger.info("This training already exists");
            return;
        }

        if (trainerDAO.findById(training.getTrainerId()).getSpeciality() != training.getType()) {
            logger.error("Training type does not match trainer speciality");
            return;
        }

        trainingDAO.create(training);
        logger.info("New Training created: {}", training);
    }

    public Training get(String id) {
        Training training = trainingDAO.findById(id);
        if (training != null) {
            logger.info("You selected Training: {}", training);
        } else {
            logger.warn("Training with ID: {} not found", id);
        }

        return null;
    }

    public List<Training> getAll() {
        List<Training> trainings = trainingDAO.getAll();
        logger.info("-----------------------------All Trainings in storage:--------------------------------");
        for (Training training : trainings) {
            logger.info("In Storage: {}", training);
        }
        logger.info("--------------------------------------------------------------------------------------");
        return trainings;
    }

}
