package com.diegohp;

import com.diegohp.config.AppConfig;
import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainee.UpdateTraineeDto;
import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.trainer.UpdateTrainerDto;
import com.diegohp.dto.training.CreateTrainingDto;
import com.diegohp.dto.user.CreateUserDto;
import com.diegohp.dto.user.UpdateUserDto;
import com.diegohp.entity.training.Training;
import com.diegohp.service.TraineeService;
import com.diegohp.service.TrainerService;
import com.diegohp.service.TrainingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;


public class Main {
    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public static void main(String[] args) {
        TraineeService traineeService = context.getBean(TraineeService.class);
        CreateTraineeDto createTraineeDto = new CreateTraineeDto(
                new CreateUserDto("Miguel", "Diaz"), new Date(), "Reseda"
        );
        traineeService.create(createTraineeDto);
        traineeService.getByUsername("Miguel.Diaz");
        traineeService.update("Miguel.Diaz", new UpdateTraineeDto(
                new UpdateUserDto("El Serpiente", null, null, null),
                null, null
        ));
        //traineeService.toggleActive("El Serpiente.Diaz", false);
        //traineeService.delete("Miguel.Diaz");
        //traineeService.delete("El Serpiente.Diaz");

        TrainerService trainerService = context.getBean(TrainerService.class);
        CreateTrainerDto trainerDto = new CreateTrainerDto(
                new CreateUserDto("Johny", "Lawrence"), 4L
        );
        trainerService.create(trainerDto);
        trainerService.update("John.Kreese", new UpdateTrainerDto(
                new UpdateUserDto("King", "Cobra", null, null),
                3L
        ));

        trainerService.update("Johny.Lawrence", new UpdateTrainerDto(
                new UpdateUserDto("King", "Cobra", null, null),
                3L
        ));

        trainerService.update("King.Cobra", new UpdateTrainerDto(
                new UpdateUserDto(null, null, null, "Iron Eagle"),
                3L
        ));

        TrainingService trainingService = context.getBean(TrainingService.class);
        trainingService.create(new CreateTrainingDto(
                1L, 1L, "Cobra Kai Training", new Date(), 10
        ));

    }
    /*-


    -
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
        traineeService.get(3L); //Message not found

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

    public static void trainings() {
        TrainingService trainingService = context.getBean(TrainingService.class);
        //get all trainings in storage
        trainingService.getAll();

        Training t1 = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");
        trainingService.create(t1); //Successful creation

        //trainingService.create(t1); //Message training already exists

        t1.setTrainerId(12L);
        trainingService.create(t1); //Error mesage, trainer does not exist
        t1.setTrainerId(1L);

        t1.setTraineeId(5L);
        trainingService.create(t1); //Error message, trainee does not exist
        t1.setTraineeId(6L);

        t1.setType(TrainingType.CARDIO);
        trainingService.create(t1); //Error message, trainig type does not match trainer speciality

        //get one training
        trainingService.get("2-4");
        trainingService.get("5-6");

        //get all trainings in storage
        trainingService.getAll();

    }

    public static void main(String[] args) throws ParseException {
        //trainees();
        //trainers();
        //trainings();
    }

     */
}
