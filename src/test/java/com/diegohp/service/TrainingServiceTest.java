package com.diegohp.service;

import com.diegohp.dao.TraineeDAO;
import com.diegohp.dao.TrainerDAO;
import com.diegohp.dao.TrainingDAO;
import com.diegohp.entity.training.Training;
import com.diegohp.entity.training.enums.TrainingType;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {
    @Mock
    private TrainingDAO trainingDAO;

    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private Logger logger;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    public void setUp() {
        trainingService.setLogger(logger);
    }

    @Test
    void testCreate_trainerDoesNotExist() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");

        when(trainerDAO.findById(training.getTrainerId())).thenReturn(null);

        trainingService.create(training);

        verify(trainerDAO).findById(eq(training.getTrainerId()));
        verify(logger).error(contains("Trainer does not exist"));
    }

    @Test
    void testCreate_traineeDoesNotExist() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");

        when(trainerDAO.findById(training.getTrainerId())).thenReturn(new Trainer());
        when(traineeDAO.findById(training.getTraineeId())).thenReturn(null);

        trainingService.create(training);

        verify(trainerDAO).findById(eq(training.getTrainerId()));
        verify(traineeDAO).findById(eq(training.getTraineeId()));

        verify(logger).error(contains("Trainee does not exist"));
    }

    @Test
    void testCreate_duplicateTraining() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");

        when(trainerDAO.findById(training.getTrainerId())).thenReturn(new Trainer());
        when(traineeDAO.findById(training.getTraineeId())).thenReturn(new Trainee());
        when(trainingDAO.findById("5-6")).thenReturn(training);

        trainingService.create(training);

        verify(trainerDAO).findById(eq(training.getTrainerId()));
        verify(traineeDAO).findById(eq(training.getTraineeId()));
        verify(trainingDAO).findById(eq("5-6"));

        verify(logger).error(contains("This training already exists"));
    }

    @Test
    void testCreate_specialityDoesNotMatch() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");
        Trainer trainer = new Trainer();
        trainer.setSpeciality(TrainingType.CALISTHENICS);

        when(trainerDAO.findById(training.getTrainerId())).thenReturn(trainer);
        when(traineeDAO.findById(training.getTraineeId())).thenReturn(new Trainee());
        when(trainingDAO.findById("5-6")).thenReturn(null);

        trainingService.create(training);

        verify(trainerDAO).findById(eq(training.getTrainerId()));
        verify(traineeDAO).findById(eq(training.getTraineeId()));
        verify(trainingDAO).findById(eq("5-6"));
        verify(logger).error(contains("Training type does not match trainer speciality"));
    }


    @Test
    void testCreate() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");
        Trainer trainer = new Trainer();
        trainer.setSpeciality(TrainingType.STRENGTH);

        when(trainerDAO.findById(training.getTrainerId())).thenReturn(trainer);
        when(traineeDAO.findById(training.getTraineeId())).thenReturn(new Trainee());
        when(trainingDAO.findById("5-6")).thenReturn(null);

        trainingService.create(training);

        verify(trainerDAO).findById(eq(training.getTrainerId()));
        verify(traineeDAO).findById(eq(training.getTraineeId()));
        verify(trainingDAO).findById(eq("5-6"));
        verify(logger).info(contains("Training Creation"));
    }

    @Test
    void testGet() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");

        when(trainingDAO.findById(anyString())).thenReturn(training);

        assertEquals(training, trainingService.get("5-6"));
        verify(trainingDAO).findById(eq("5-6"));
        verify(logger).info(contains("Select Training"));
    }

    @Test
    void testGetNotFound() {
        when(trainingDAO.findById("5-6")).thenReturn(null);

        assertNull(trainingService.get("5-6"));
    }

    @Test
    void testGetAll() {
        Training training1 = new Training(6L, 5L, "Late Strenght Training", TrainingType.STRENGTH, new Date(), "PT1H");
        Training training2 = new Training(3L, 4L, "Morning Weight Training", TrainingType.WEIGHTLIFTING, new Date(), "PT1H");
        List<Training> allTrainings = Arrays.asList(training1, training2);

        when(trainingService.getAll()).thenReturn(allTrainings);

        List<Training> actual = trainingService.getAll();

        assertEquals(allTrainings.size(), actual.size());
        assertEquals(allTrainings, actual);
        //verify(logger).info(contains("Select All Trainings"));
    }
}