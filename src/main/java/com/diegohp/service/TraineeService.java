package com.diegohp.service;

import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainee.UpdateTraineeDto;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.TraineeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TraineeService {
    private final TraineeRepository repository;
    private final UserService userService;
    private Logger logger;

    @Autowired
    public TraineeService(TraineeRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Transactional
    public void create(CreateTraineeDto traineeDto) {
        logger.info("-------------------------Trainee Creation----------------------------------------------");
        User user = userService.create(traineeDto.getUserDto().getFirstName(), traineeDto.getUserDto().getLastName());
        Trainee trainee = new Trainee(traineeDto.getDateOfBirth(), traineeDto.getAddress());
        trainee.setUser(user);
        repository.create(trainee);
        logger.info("New Trainee created: {}", trainee);
    }

    public Optional<Trainee> getById(Long id) {
        logger.info("-------------------------------Select Trainee By Id-----------------------------------------");
        Optional<Trainee> trainee = repository.getById(id);
        if (trainee.isPresent()) {
            logger.info("You selected Trainee with ID {}: {}", id, trainee.get());
        } else {
            logger.warn("Trainee with ID: {} not found", id);
        }
        return trainee;
    }

    @Transactional
    public Optional<Trainee> getByUsername(String username) {
        logger.info("-------------------------------Select Trainee By Username-----------------------------------------");
        Optional<Trainee> trainee = repository.getByUsername(username);
        if (trainee.isPresent()) {
            logger.info("You selected Trainee with username {}: {}", username, trainee.get());
        } else {
            logger.error("Trainee with username: {} not found", username);
        }
        return trainee;
    }

    @Transactional
    public void update(String username, UpdateTraineeDto traineeDto) {
        logger.info("-------------------------------Update Trainee-----------------------------------------");
        Optional<Trainee> trainee = this.getByUsername(username);
        if (trainee.isPresent()) {
            Trainee original = new Trainee(trainee.get());
            User user = trainee.get().getUser();
            userService.updateUser(user, traineeDto.getUserDto());
            trainee.get().setDateOfBirth(traineeDto.getDateOfBirth());
            trainee.get().setAddress(traineeDto.getAddress());
            repository.update(trainee.get());
            logger.info("Trainee : {} has been updated from {} to {}", username, original, trainee.get());
        }
    }

    public void toggleActive(String username, Boolean active) {
        logger.info("--------------------------------------- Modifying Trainee Status -----------------------------------");
        userService.toggleActive(username, active);
    }

    @Transactional
    public void delete(String username) {
        logger.info("---------------------------------------- Delete Trainee --------------------------------------------");
        Optional<Trainee> trainee = this.getByUsername(username);
        if (trainee.isPresent()) {
            repository.delete(trainee.get());
            logger.info("Trainee with username {} has been deleted", username);
        }
    }
}

