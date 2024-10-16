package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.entity.user.Trainee;
import com.diegohp.service.TraineeService;
import com.diegohp.storage.TraineeStorage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TraineeStorage traineeStorage = context.getBean(TraineeStorage.class);
        for (Trainee t : traineeStorage.getStorage().values()) {
            System.out.println(t.toString());
        }
    }
}
