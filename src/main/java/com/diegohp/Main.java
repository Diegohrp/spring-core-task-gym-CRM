package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import com.diegohp.service.TraineeService;
import com.diegohp.service.TrainerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


    private static void trainees() throws ParseException {
        TraineeService traineeService = context.getBean(TraineeService.class);
        //Show all Trainees in storage
        traineeService.getAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Trainee t1 = new Trainee("Alice", "Smith", sdf.parse("15-08-1992"), "123 Main Street");
        Trainee t2 = new Trainee("Bob", "Johnson", sdf.parse("12-05-1987"), "456 Oak Avenue");
        Trainee t3 = new Trainee("Charlie", "Brown", sdf.parse("22-07-1990"), "789 Pine Road");
        Trainee t4 = new Trainee("Emma", "Taylor", sdf.parse("04-11-1995"), "202 Cedar Boulevard");

        //Create Trainees
        traineeService.create(t1);
        traineeService.create(t2);
        traineeService.create(t3);
        traineeService.create(t4);

        //Add Trainees with the same first and last name
        traineeService.create(t2);
        traineeService.create(t2);
        traineeService.create(t2);

        //Update Trainees
        Trainee newData = new Trainee("Bob", "Johnson", new Date(), "New Address");
        traineeService.update(1L, newData);

        //Remove Trainees
        traineeService.delete(3L);
        traineeService.delete(4L);
        traineeService.delete(5L);

        //Get one Trainee
        traineeService.get(6L);
        traineeService.get(3L);

        //Show all Trainees in storage
        traineeService.getAll();
    }

    public static void trainers() {
        TrainerService trainerService = context.getBean(TrainerService.class);

        //Get All Trainers in sotrage
        trainerService.getAll();

        Trainer t1 = new Trainer("Carlos", "Gomez", TrainingType.STRENGTH);
        Trainer t2 = new Trainer("Laura", "Perez", TrainingType.CYCLING);
        Trainer t3 = new Trainer("Miguel", "Diaz", TrainingType.BOXING);
        Trainer t4 = new Trainer("Elena", "Martinez", TrainingType.CARDIO);

        //Create Trainers
        trainerService.create(t1);
        trainerService.create(t2);
        trainerService.create(t3);
        trainerService.create(t4);

        //Add Trainers with same fisrtname and lastname
        trainerService.create(t3);
        trainerService.create(t3);
        trainerService.create(t3);

        //Update Trainers
        Trainer newData = new Trainer("Miguel", "Perez", TrainingType.CALISTHENICS);
        trainerService.update(6L, newData);

        //Get all Trainers in storage
        trainerService.getAll();

    }

    public static void main(String[] args) throws ParseException {
        trainees();
        trainers();
    }
}
