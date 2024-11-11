package com.diegohp.dao;

import com.diegohp.entity.training.Training;
import com.diegohp.entity.training.enums.TrainingTypes;
import com.diegohp.storage.TrainingStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingDAOTest {
    @Mock
    private TrainingStorage mockStorage;
    private Map<String, Training> trainingStorage;
    private TrainingDAO trainingDAO;

    @BeforeEach
    public void setUp() {
        this.trainingStorage = new HashMap<>();
        when(mockStorage.getStorage()).thenReturn(trainingStorage);
        this.trainingDAO = new TrainingDAO(mockStorage);
    }

    @Test
    void testCreate() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingTypes.STRENGTH, new Date(), "PT1H");
        trainingDAO.create(training);
        assertEquals(training, trainingStorage.get("5-6"));
    }

    @Test
    void testFindById() {
        Training training = new Training(6L, 5L, "Late Strenght Training", TrainingTypes.STRENGTH, new Date(), "PT1H");
        trainingStorage.put("5-6", training);
        assertEquals(training, trainingDAO.findById("5-6"));
        assertNull(trainingDAO.findById("nonexistent"));
    }

    @Test
    void getAll() {
        Training training1 = new Training(6L, 5L, "Late Strenght Training", TrainingTypes.STRENGTH, new Date(), "PT1H");
        Training training2 = new Training(3L, 4L, "Morning Weight Training", TrainingTypes.WEIGHTLIFTING, new Date(), "PT1H");
        trainingStorage.put("5-6", training1);
        trainingStorage.put("4-3", training2);
        List<Training> allTrainings = trainingDAO.getAll();
        assertEquals(2, allTrainings.size());
        assertTrue(allTrainings.contains(training1));
        assertTrue(allTrainings.contains(training2));
    }
}