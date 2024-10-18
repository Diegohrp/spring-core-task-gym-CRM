package com.diegohp.service;

import com.diegohp.dao.TraineeDAO;
import com.diegohp.entity.user.Trainee;
import com.diegohp.utils.UserCredentialsGenerator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TraineeService extends UserService {
    @Value("${trainee.data.startFromId}")
    private Long idGen;
    private final TraineeDAO traineeDAO;
    private final UserCredentialsGenerator userCredentialsGenerator;
    private Logger logger;

    @Autowired
    public TraineeService(TraineeDAO traineeDAO, UserCredentialsGenerator userCredentialsGenerator) {
        this.traineeDAO = traineeDAO;
        this.userCredentialsGenerator = userCredentialsGenerator;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Boolean userNameExists(String username) {
        return traineeDAO.findByUsername(username) != null;
    }

    public void create(Trainee trainee) {
        logger.info("-------------------------Trainee Creation----------------------------------------------");
        Trainee copy = new Trainee(trainee);
        userCredentialsGenerator.assignCredentials(copy, this::userNameExists);
        copy.setId(idGen++);
        traineeDAO.create(copy);
        logger.info("New Trainee created: {}", copy);

    }

    public Trainee get(Long id) {
        logger.info("-------------------------------Select Trainee-----------------------------------------");
        Trainee trainee = traineeDAO.findById(id);
        if (trainee != null) {
            logger.info("You selected Trainee: {}", trainee);
        } else {
            logger.warn("Trainee with ID: {} not found", id);
        }

        return trainee;
    }

    public void update(Long id, Trainee data) {
        logger.info("-------------------------------Update Trainee-----------------------------------------");
        Trainee original = traineeDAO.findById(id);
        Trainee updated = new Trainee(original);
        super.updateUser(updated, data);
        if (data.getDateOfBirth() != null)
            updated.setDateOfBirth(data.getDateOfBirth());
        if (data.getAddress() != null)
            updated.setAddress(data.getAddress());

        if (super.usernameMustChange(original, updated)) {
            updated.setUsername(userCredentialsGenerator
                    .generateUsername(updated.getFirstName(), updated.getLastName(), this::userNameExists));
        }

        traineeDAO.update(id, updated);
        logger.info("Trainee ID: {} has been updated from {} to {}", id, original, updated);
    }

    public void delete(Long id) {
        logger.info("-------------------------------Delete Trainee-----------------------------------------");
        traineeDAO.delete(id);
        logger.info("Trainee with ID {} has been deleted", id);
    }

    public List<Trainee> getAll() {
        logger.info("-------------------------------Get All Trainees-----------------------------------------");
        for (Trainee trainee : traineeDAO.getAll()) {
            logger.info("In Storage: {}", trainee);
        }

        return traineeDAO.getAll();
    }

}

