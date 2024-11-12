package com.diegohp.service;

import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.trainer.UpdateTrainerDto;

import com.diegohp.entity.user.Trainer;
import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.TrainerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TrainerService {
    private UserService userService;
    private TrainingTypeService trainingTypeService;
    private TrainerRepository repository;
    private Logger logger;

    @Autowired
    public TrainerService(UserService userService, TrainingTypeService trainingTypeService, TrainerRepository repository) {
        this.userService = userService;
        this.trainingTypeService = trainingTypeService;
        this.repository = repository;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Transactional
    public String create(CreateTrainerDto trainerDto) {
        logger.info("---------------------------------------------- Trainer Creation ----------------------------------------------");
        Trainer trainer = new Trainer();
        try {
            trainer.setSpeciality(trainingTypeService.getById(trainerDto.getSpeciality()));
            User user = userService.create(trainerDto.getUserDto().getFirstName(), trainerDto.getUserDto().getLastName());
            trainer.setUser(user);
            repository.create(trainer);
            logger.info("New Trainer created: {}", trainer);
            return trainer.getUser().getPassword();
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @Transactional
    public Optional<Trainer> getByUsername(String username) {
        logger.info("-------------------------------Select Trainer By Username-----------------------------------------");
        Optional<Trainer> trainer = repository.getByUsername(username);
        if (trainer.isPresent()) {
            logger.info("You selected Trainer with username {}: {}", username, trainer.get());
        } else {
            logger.error("Trainer with username: {} not found", username);
        }
        return trainer;
    }

    @Transactional
    public void update(String username, UpdateTrainerDto trainerDto) {
        logger.info("-------------------------------Update Trainer-----------------------------------------");
        Optional<Trainer> trainer = this.getByUsername(username);
        if (trainer.isEmpty()) {
            return;
        }
        Trainer original = new Trainer(trainer.get());
        userService.updateUser(trainer.get().getUser(), trainerDto.getUserDto());
        try {
            Long specialityId = trainerDto.getSpeciality();
            if (specialityId != null) {
                trainer.get().setSpeciality(trainingTypeService.getById(specialityId));
            }
            repository.update(trainer.get());
            logger.info("Trainee : {} has been updated from {} to {}", username, original, trainer.get());
        } catch (EntityNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public void toggleActive(String username, Boolean active) {
        logger.info("--------------------------------------- Modifying Trainer Status -----------------------------------");
        userService.toggleActive(username, active);
    }

    public Optional<Trainer> getById(Long id) {
        logger.info("-------------------------------Select Trainer By Id-----------------------------------------");
        Optional<Trainer> trainer = repository.getById(id);
        if (trainer.isEmpty()) {
            throw new EntityNotFoundException("Trainer with ID: " + id + " not found");
        }
        logger.info("You selected Trainer with ID {}: {}", id, trainer.get());
        return trainer;
    }
}
