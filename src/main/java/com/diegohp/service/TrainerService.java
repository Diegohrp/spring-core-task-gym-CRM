package com.diegohp.service;

import com.diegohp.dao.TrainerDAO;
import com.diegohp.entity.user.Trainer;
import com.diegohp.utils.UserCredentialsGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    /*@Value("${trainer.data.startFromId}")
    private Long idGen;
    private final TrainerDAO trainerDAO;
    private final UserCredentialsGenerator userCredentialsGenerator;
    private Logger logger;

    @Autowired
    public TrainerService(TrainerDAO trainerDAO, UserCredentialsGenerator userCredentialsGenerator) {
        this.trainerDAO = trainerDAO;
        this.userCredentialsGenerator = userCredentialsGenerator;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Boolean userNameExists(String username) {
        return trainerDAO.findByUsername(username) != null;
    }

    public void create(Trainer trainer) {
        logger.info("-------------------------------Trainer Creation-----------------------------------------");
        Trainer copy = new Trainer(trainer);
        userCredentialsGenerator.assignCredentials(copy, this::userNameExists);
        copy.setId(idGen++);
        trainerDAO.create(copy);
        logger.info("New Trainer created: {}", copy);
    }

    public Trainer get(Long id) {
        logger.info("-------------------------------Get Trainer-----------------------------------------");
        Trainer trainer = trainerDAO.findById(id);
        if (trainer != null) {
            logger.info("You selected Trainer: {}", trainer);
        } else {
            logger.warn("Trainer with ID: {} not found", id);
        }
        return trainer;
    }

    public List<Trainer> getAll() {
        logger.info("-------------------------------Get All Trainers-----------------------------------------");
        for (Trainer trainer : trainerDAO.getAll()) {
            logger.info("In Storage: {}", trainer);
        }
        return trainerDAO.getAll();
    }

    public void update(Long id, Trainer data) {
        logger.info("-------------------------------Update Trainer-----------------------------------------");
        Trainer original = trainerDAO.findById(id);
        Trainer updated = new Trainer(original);
        super.updateUser(updated, data);

        if (data.getSpeciality() != null)
            updated.setSpeciality(data.getSpeciality());

        if (super.usernameMustChange(original, updated)) {
            updated.setUsername(userCredentialsGenerator
                    .generateUsername(updated.getFirstName(), updated.getLastName(), this::userNameExists));
        }

        trainerDAO.update(id, updated);
        logger.info("Trainer ID: {} has been updated from {} to {}", id, original, updated);
    }

     */

}
