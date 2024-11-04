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

    public Optional<Trainee> getByUsername(String username) {
        logger.info("-------------------------------Select Trainee By Username-----------------------------------------");
        Optional<Trainee> trainee = repository.getByUsername(username);
        if (trainee.isPresent()) {
            logger.info("You selected Trainee with username {}: {}", username, trainee.get());
        } else {
            logger.warn("Trainee with username: {} not found", username);
        }
        return trainee;
    }

    @Transactional
    public void update(String username, UpdateTraineeDto traineeDto) {
        logger.info("-------------------------------Update Trainee-----------------------------------------");
        Optional<Trainee> trainee = repository.getByUsername(username);
        if (trainee.isEmpty()) {
            logger.error("The user with username {} does not exist", username);
            return;
        }
        logger.info("Gotten Trainee: {}", trainee.get());
        Trainee original = new Trainee(trainee.get());
        User user = trainee.get().getUser();
        userService.updateUser(user, traineeDto.getUserDto());
        //trainee.get().setUser(user);
        trainee.get().setDateOfBirth(traineeDto.getDateOfBirth());
        trainee.get().setAddress(traineeDto.getAddress());
        repository.update(trainee.get());
        logger.info("Trainee : {} has been updated from {} to {}", username, original, trainee.get());
    }



    /*
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
        //logger.info("Trainee ID: {} has been updated from {} to {}", id, original, updated);
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

 */
}

