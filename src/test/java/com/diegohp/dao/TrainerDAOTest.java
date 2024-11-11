package com.diegohp.dao;

import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.entity.user.Trainer;
import com.diegohp.storage.TrainerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerDAOTest {
    @Mock
    private TrainerStorage mockStorage;
    private TrainerDAO trainerDAO;
    private Map<Long, Trainer> trainerStorage;

    @BeforeEach
    public void setUp() {
        trainerStorage = new HashMap<>();
        when(mockStorage.getStorage()).thenReturn(trainerStorage);
        this.trainerDAO = new TrainerDAO(mockStorage);
    }

    @Test
    void testCreate() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        trainerDAO.create(trainer);
        assertEquals(trainer, trainerStorage.get(trainer.getId()));
    }

    @Test
    void testUpdate() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        trainerDAO.update(1L, trainer);
        assertEquals(trainer, trainerStorage.get(trainer.getId()));
    }

    @Test
    void testFindById() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        trainerStorage.put(trainer.getId(), trainer);
        assertEquals(trainer, trainerDAO.findById(1L));
    }

    @Test
    void testFindByUsername() {
        Trainer trainer = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        trainerStorage.put(trainer.getId(), trainer);
        assertEquals(trainer, trainerDAO.findByUsername(trainer.getUsername()));
        assertNull(trainerDAO.findByUsername("nonexistent"));
    }

    @Test
    void testGetAll() {
        Trainer trainer1 = new Trainer(1L, "Miguel", "Diaz", "Miguel.Diaz", "8Wdh3&pd*)", true, TrainingTypes.BOXING);
        Trainer trainer2 = new Trainer(2L, "Jane", "Doe", "Jane.Doe", "4Et49&kP<-", true, TrainingTypes.CYCLING);
        trainerStorage.put(1L, trainer1);
        trainerStorage.put(2L, trainer2);
        List<Trainer> allTrainers = trainerDAO.getAll();
        assertEquals(2, allTrainers.size());
        assertTrue(allTrainers.contains(trainer1));
        assertTrue(allTrainers.contains(trainer2));
    }
}