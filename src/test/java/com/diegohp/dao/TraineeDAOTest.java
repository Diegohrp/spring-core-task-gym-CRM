package com.diegohp.dao;

import com.diegohp.entity.user.Trainee;
import com.diegohp.storage.TraineeStorage;
import org.junit.jupiter.api.BeforeAll;
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
class TraineeDAOTest {
    @Mock
    private TraineeStorage mockStorage;
    private TraineeDAO traineeDAO;
    private Map<Long, Trainee> traineeStorage;

    @BeforeEach
    void setUp() {
        traineeStorage = new HashMap<>();
        when(mockStorage.getStorage()).thenReturn(traineeStorage);
        traineeDAO = new TraineeDAO(mockStorage);
    }

    @Test
    void testCreate() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        traineeDAO.create(trainee);
        assertEquals(trainee, traineeStorage.get(trainee.getId()));
    }

    @Test
    void testUpdate() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        traineeDAO.update(1L, trainee);
        assertEquals(trainee, traineeStorage.get(trainee.getId()));
    }

    @Test
    void testFindById() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        traineeStorage.put(trainee.getId(), trainee);
        assertEquals(trainee, traineeDAO.findById(trainee.getId()));
    }

    @Test
    void testFindByUsername() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        traineeStorage.put(trainee.getId(), trainee);
        assertEquals(trainee, traineeDAO.findByUsername(trainee.getUsername()));
        assertNull(traineeDAO.findByUsername("nonexistent"));
    }

    @Test
    void testDelete() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        traineeStorage.put(1L, trainee);
        traineeDAO.delete(1L);
        assertNull(traineeStorage.get(1L));
    }

    @Test
    void testGetAll() {
        Trainee trainee1 = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        Trainee trainee2 = new Trainee(2L, "Jane", "Doe", "Jane.Doe", "4Et49&kP<-", true, new Date(), "013 Street Some City");
        traineeStorage.put(1L, trainee1);
        traineeStorage.put(2L, trainee2);
        List<Trainee> allTrainees = traineeDAO.getAll();
        assertEquals(2, allTrainees.size());
        assertTrue(allTrainees.contains(trainee1));
        assertTrue(allTrainees.contains(trainee2));
    }

}