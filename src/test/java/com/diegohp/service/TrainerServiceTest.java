package com.diegohp.service;

import com.diegohp.dao.TrainerDAO;
import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.entity.user.Trainer;
import com.diegohp.utils.UserCredentialsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Mock
    private TrainerDAO trainerDAO;

    @Mock
    private UserCredentialsGenerator userCredentialsGenerator;

    @Mock
    private Logger logger;

    @InjectMocks
    private TrainerService trainerService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(trainerService, "idGen", 5L);
        trainerService.setLogger(logger);
    }

    @Test
    void testUserNameExists() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);

        when(trainerDAO.findByUsername("existing")).thenReturn(trainer);
        when(trainerDAO.findByUsername("nonexisting")).thenReturn(null);

        assertTrue(trainerService.userNameExists("existing"));
        assertFalse(trainerService.userNameExists("nonexisting"));
    }

    @Test
    void testCreate() {
        Trainer trainer1 = new Trainer("Miguel", "Diaz", TrainingTypes.BOXING);
        Trainer trainer2 = new Trainer("Jane", "Doe", TrainingTypes.CYCLING);

        doNothing().when(userCredentialsGenerator).assignCredentials(any(Trainer.class), any());

        trainerService.create(trainer1);
        verify(trainerDAO).create(argThat(t -> t.getId() == 5L));
        verify(logger).info(contains("Trainer Creation"));

        trainerService.create(trainer2);
        verify(trainerDAO).create(argThat(t -> t.getId() == 6L));
    }

    @Test
    void testGet() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        when(trainerDAO.findById(trainer.getId())).thenReturn(trainer);

        assertEquals(trainer, trainerService.get(1L));
        verify(logger).info(contains("Get Trainer"));
    }

    @Test
    void testGetNotFound() {
        when(trainerDAO.findById(4L)).thenReturn(null);
        assertNull(trainerService.get(4L));
        verify(logger).info(contains("Get Trainer"));
    }

    @Test
    void testGetAll() {
        Trainer trainer1 = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        Trainer trainer2 = new Trainer(2L, "Jane", "Doe", "Jane.Doe", "4Et49&kP<-", true, TrainingTypes.CYCLING);

        List<Trainer> trainers = Arrays.asList(trainer1, trainer2);
        when(trainerDAO.getAll()).thenReturn(trainers);

        assertEquals(trainers, trainerService.getAll());
        verify(logger).info(contains("Get All Trainers"));
    }

    @Test
    void testUpdate() {
        Trainer original = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        Trainer updated = new Trainer(original);
        updated.setFirstName("John");
        updated.setLastName("Doe");
        updated.setSpeciality(TrainingTypes.STRENGTH);

        when(trainerDAO.findById(original.getId())).thenReturn(original);
        when(userCredentialsGenerator.generateUsername(anyString(), anyString(), any())).thenReturn("John.Doe");

        trainerService.update(1L, updated);
        verify(trainerDAO).update(eq(1L), argThat(t -> t.getFirstName().equals("John")
                && t.getLastName().equals("Doe")
                && t.getSpeciality() == TrainingTypes.STRENGTH));
        verify(logger).info(contains("Update Trainer"));
    }


}