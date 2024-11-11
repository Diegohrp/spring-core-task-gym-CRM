package com.diegohp.service;

import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.repository.interfaces.TrainingTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class TrainingTypeService {
    private TrainingTypeRepository repository;
    private Logger logger;

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Autowired
    public TrainingTypeService(TrainingTypeRepository repository) {
        this.repository = repository;
    }

    public void create(TrainingType trainingType) {
        logger.info("------------------------------------------ Create Training Type ---------------------------------------");
        repository.create(trainingType);
        logger.info("Training Type: {} has been created", trainingType.getName());
    }

    public TrainingType getById(Long id) {
        Optional<TrainingType> trainingType = repository.getById(id);
        if (trainingType.isEmpty()) {
            throw new EntityNotFoundException("This training type does not exist");
        }
        return trainingType.get();
    }

}
