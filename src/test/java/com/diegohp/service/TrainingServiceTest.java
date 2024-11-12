package com.diegohp.service;

import com.diegohp.dto.training.CreateTrainingDto;
import com.diegohp.entity.training.Training;
import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {
    @Mock
    private TrainingRepository trainingRepository;
    @Mock
    private TraineeService traineeService;
    @Mock
    private TrainerService trainerService;
    @Mock
    private Logger logger;
    @InjectMocks
    private TrainingService trainingService;
    private CreateTrainingDto trainingDto;

    private Training training;

    @BeforeEach
    void setUp() {
        trainingDto = new CreateTrainingDto(1L, 2L, "Cardio Session", new Date(), 60);
        trainingService.setLogger(logger);

        Trainer trainer = new Trainer();
        trainer.setUser(new User("John", "Doe"));
        trainer.setSpeciality(new TrainingType("Some Training"));
        Trainee trainee = new Trainee(new Date(), "123 Main St");
        trainee.setUser(new User("Jane", "Doe"));
        training = new Training(trainee, trainer, "Some training", new TrainingType("Some type"), new Date(), 40);
    }

    @Test
    void testCreate() {
        Trainee trainee = new Trainee();
        Trainer trainer = new Trainer();
        when(traineeService.getById(anyLong())).thenReturn(Optional.of(trainee));
        when(trainerService.getById(anyLong())).thenReturn(Optional.of(trainer));
        doNothing().when(trainingRepository).create(any(Training.class));
        trainingService.create(trainingDto);
        verify(traineeService, times(1)).getById(anyLong());
        verify(trainerService, times(1)).getById(anyLong());
        verify(trainingRepository, times(1)).create(any(Training.class));
    }

    @Test
    void testGetTraineeTrainings() {
        List<Training> trainings = List.of(training);
        when(trainingRepository.getByCriteria(anyString(), any(Date.class), any(Date.class), anyString(), anyLong())).thenReturn(Optional.of(trainings));
        Optional<List<Training>> result = trainingService.getTraineeTrainings("trainee", new Date(), new Date(), "trainerName", 1L);
        verify(trainingRepository, times(1)).getByCriteria(anyString(), any(Date.class), any(Date.class), anyString(), anyLong());
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }

    @Test
    void testGetTrainerTrainings() {
        List<Training> trainings = List.of(training);
        when(trainingRepository.getByCriteria(anyString(), any(Date.class), any(Date.class), anyString())).thenReturn(Optional.of(trainings));
        Optional<List<Training>> result = trainingService.getTrainerTrainings("trainer", new Date(), new Date(), "traineeName");
        verify(trainingRepository, times(1)).getByCriteria(anyString(), any(Date.class), any(Date.class), anyString());
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }
}