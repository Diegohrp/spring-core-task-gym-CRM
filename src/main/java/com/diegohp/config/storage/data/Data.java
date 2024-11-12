package com.diegohp.config.storage.data;

import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.training.CreateTrainingDto;
import com.diegohp.dto.user.CreateUserDto;

import com.diegohp.entity.training.enums.TrainingTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Data {

    @Bean
    public TrainingTypes[] trainingTypes() {
        return TrainingTypes.values();
    }

    @Bean
    public List<CreateTraineeDto> traineeDtos() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CreateTraineeDto> trainees = new ArrayList<>();
        try {
            trainees.add(new CreateTraineeDto(new CreateUserDto("Juan", "Pérez"), dateFormat.parse("1995-05-15"), "Calle Falsa 123, Ciudad de México"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("María", "González"), dateFormat.parse("1990-08-22"), "Avenida Siempre Viva 456, Guadalajara"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Carlos", "Hernández"), dateFormat.parse("1992-03-11"), "Calle del Olivo 789, Monterrey"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Ana", "Martínez"), dateFormat.parse("1988-12-30"), "Boulevard de la Paz 321, Puebla"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Luis", "Fernández"), dateFormat.parse("1993-07-09"), "Avenida Central 654, Toluca"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Marta", "Rodríguez"), dateFormat.parse("1997-01-25"), "Calle de los Almendros 987, Cancún"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Jorge", "López"), dateFormat.parse("1991-11-14"), "Calle de la Luna 654, Mérida"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Elena", "Morales"), dateFormat.parse("1996-04-18"), "Avenida del Sol 321, Tijuana"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Pablo", "Ramírez"), dateFormat.parse("1994-09-23"), "Calle de las Flores 432, Chihuahua"));
            trainees.add(new CreateTraineeDto(new CreateUserDto("Sofía", "Vargas"), dateFormat.parse("1998-10-05"), "Avenida de los Pinos 876, León"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return trainees;
    }

    @Bean
    List<CreateTrainerDto> trainerDtos() {
        List<CreateTrainerDto> trainers = new ArrayList<>();
        trainers.add(new CreateTrainerDto(new CreateUserDto("Alejandro", "Mendoza"), 1L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Beatriz", "Ortega"), 2L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Carmen", "Núñez"), 3L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("David", "Ruiz"), 4L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Elena", "Sánchez"), 5L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Fernando", "Díaz"), 6L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Gabriela", "Ramírez"), 7L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Héctor", "García"), 8L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Isabel", "Vega"), 9L));
        trainers.add(new CreateTrainerDto(new CreateUserDto("Jorge", "Castillo"), 1L));
        return trainers;
    }

    @Bean
    List<CreateTrainingDto> trainingDtos() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CreateTrainingDto> gymTrainings = new ArrayList<>();
        try {
            gymTrainings.add(new CreateTrainingDto(1L, 1L, "Endurance Challenge", dateFormat.parse("2023-01-10"), 45));
            gymTrainings.add(new CreateTrainingDto(2L, 2L, "Heavy Lifting Mastery", dateFormat.parse("2023-01-15"), 60));
            gymTrainings.add(new CreateTrainingDto(1L, 3L, "Core Strength Builder", dateFormat.parse("2023-01-20"), 50));
            gymTrainings.add(new CreateTrainingDto(3L, 4L, "Power Boost Session", dateFormat.parse("2023-01-25"), 75));
            gymTrainings.add(new CreateTrainingDto(4L, 5L, "Muscle Sculpting Routine", dateFormat.parse("2023-02-01"), 90));
            gymTrainings.add(new CreateTrainingDto(2L, 6L, "Pilates Precision Flow", dateFormat.parse("2023-02-05"), 60));
            gymTrainings.add(new CreateTrainingDto(1L, 7L, "Full Body Calisthenics", dateFormat.parse("2023-02-10"), 40));
            gymTrainings.add(new CreateTrainingDto(5L, 8L, "Boxing Combat Skills", dateFormat.parse("2023-02-15"), 55));
            gymTrainings.add(new CreateTrainingDto(3L, 9L, "Endurance Cycling Journey", dateFormat.parse("2023-02-20"), 50));
            gymTrainings.add(new CreateTrainingDto(4L, 1L, "Cardio Fat Burn Blast", dateFormat.parse("2023-02-25"), 45));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return gymTrainings;
    }
}
