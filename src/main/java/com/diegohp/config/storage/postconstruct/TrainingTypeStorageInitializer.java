package com.diegohp.config.storage.postconstruct;

import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.service.TrainingTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class TrainingTypeStorageInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final TrainingTypeService service;

    @Autowired
    public TrainingTypeStorageInitializer(TrainingTypeService service) {
        this.service = service;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (TrainingTypes t : TrainingTypes.values()) {
            TrainingType trainingType = new TrainingType(t.getName());
            service.create(trainingType);
        }
    }
}
