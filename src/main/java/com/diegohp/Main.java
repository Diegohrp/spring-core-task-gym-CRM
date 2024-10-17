package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.entity.user.Trainee;
import com.diegohp.service.TraineeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


    private static void trainees() throws ParseException {
        TraineeService traineeService = context.getBean(TraineeService.class);
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

        //Add Trainees with the same firt and last name
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

        traineeService.getAll();
    }

    public static void main(String[] args) throws ParseException {
        trainees();
    }
}
