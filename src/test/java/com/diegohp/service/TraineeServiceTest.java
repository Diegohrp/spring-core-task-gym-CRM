package com.diegohp.service;

import com.diegohp.dao.TraineeDAO;
import com.diegohp.entity.user.Trainee;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {
    @Mock
    private TraineeDAO traineeDAO;

    @Mock
    private UserCredentialsGenerator userCredentialsGenerator;

    @Mock
    private Logger logger;

    @InjectMocks
    private TraineeService traineeService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(traineeService, "idGen", 5L);
        traineeService.setLogger(logger);
    }


    @Test
    void testUserNameExists() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");

        when(traineeDAO.findByUsername("existing")).thenReturn(trainee);
        when(traineeDAO.findByUsername("nonexisting")).thenReturn(null);

        assertTrue(traineeService.userNameExists("existing"));
        assertFalse(traineeService.userNameExists("nonexisting"));
    }

    @Test
    void testCreate() {
        Trainee trainee1 = new Trainee("John", "Doe", new Date(), "013 Street Some City");
        Trainee trainee2 = new Trainee("Jane", "Doe", new Date(), "013 Street Some City");

        doNothing().when(userCredentialsGenerator).assignCredentials(any(Trainee.class), any());

        traineeService.create(trainee1);
        verify(traineeDAO).create(argThat(t -> t.getId() == 5L));
        verify(logger).info(contains("Trainee Creation"));

        traineeService.create(trainee2);
        verify(traineeDAO).create(argThat(t -> t.getId() == 6L));

    }

    @Test
    void testGet() {
        Trainee trainee = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");

        when(traineeDAO.findById(trainee.getId())).thenReturn(trainee);

        assertEquals(trainee, traineeService.get(1L));
        verify(logger).info(contains("Select Trainee"));

    }

    @Test
    void testGetNotFound() {
        when(traineeDAO.findById(4L)).thenReturn(null);
        assertNull(traineeService.get(4L));
        verify(logger).info(contains("Select Trainee"));
    }

    @Test
    void testUpdate() {
        Trainee original = new Trainee(1L, "John", "Doe", "John.Doe", "3Ue47&hO*/", true, new Date(), "013 Street Some City");
        Trainee updated = new Trainee(original);
        updated.setFirstName("Jane");
        updated.setLastName("Who");

        when(traineeDAO.findById(original.getId())).thenReturn(original);
        when(userCredentialsGenerator.generateUsername(anyString(), anyString(), any())).thenReturn("Jane.Who");

        traineeService.update(1L, updated);
        verify(traineeDAO).update(eq(1L), argThat(t -> t.getFirstName().equals("Jane") && t.getLastName().equals("Who")));
        verify(logger).info(contains("Update Trainee"));
    }

    @Test
    void testDelete() {
        traineeService.delete(1L);

        verify(traineeDAO).delete(eq(1L));
        verify(logger).info(contains("Delete Trainee"));
    }

    @Test
    void getAll() {
        Trainee trainee1 = new Trainee("John", "Doe", new Date(), "013 Street Some City");
        Trainee trainee2 = new Trainee("Jane", "Doe", new Date(), "013 Street Some City");

        List<Trainee> trainees = Arrays.asList(trainee1, trainee2);
        when(traineeDAO.getAll()).thenReturn(trainees);

        assertEquals(trainees, traineeService.getAll());
        verify(logger).info(contains("Get All Trainees"));
    }
}