package com.diegohp.service;

import com.diegohp.dto.trainer.CreateTrainerDto;
import com.diegohp.dto.trainer.UpdateTrainerDto;
import com.diegohp.dto.user.CreateUserDto;
import com.diegohp.dto.user.UpdateUserDto;
import com.diegohp.entity.training.TrainingType;
import com.diegohp.entity.user.Trainer;

import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.TrainerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {
    @Mock
    private TrainerRepository trainerRepository;
    @Mock
    private UserService userService;
    @Mock
    private TrainingTypeService trainingTypeService;
    @Mock
    private Logger logger;
    @InjectMocks
    private TrainerService trainerService;
    private CreateTrainerDto trainerDto;

    @BeforeEach
    void setUp() {
        CreateUserDto userDto = new CreateUserDto("John", "Doe");
        trainerDto = new CreateTrainerDto(userDto, 1L);
        trainerService.setLogger(logger);
    }

    @Test
    void testCreate() {
        when(trainingTypeService.getById(anyLong())).thenReturn(new TrainingType());
        when(userService.create(anyString(), anyString())).thenReturn(new User());
        doNothing().when(trainerRepository).create(any(Trainer.class));
        String password = trainerService.create(trainerDto);
        verify(trainingTypeService, times(1)).getById(anyLong());
        verify(userService, times(1)).create(anyString(), anyString());
        verify(trainerRepository, times(1)).create(any(Trainer.class));
    }

    @Test
    void testGetByUsername() {
        Trainer trainer = new Trainer();
        when(trainerRepository.getByUsername(anyString())).thenReturn(Optional.of(trainer));
        Optional<Trainer> result = trainerService.getByUsername("johndoe");
        verify(trainerRepository, times(1)).getByUsername(anyString());
        assertTrue(result.isPresent());
    }

    @Test
    void testUpdate() {
        Trainer trainer = new Trainer();
        trainer.setUser(new User("John", "Doe"));
        trainer.setSpeciality(new TrainingType("Some Training"));
        UpdateTrainerDto updateDto = new UpdateTrainerDto(new UpdateUserDto("Jane", "Doe", null, null), 2L);
        when(trainerRepository.getByUsername(anyString())).thenReturn(Optional.of(trainer));
        when(trainingTypeService.getById(anyLong())).thenReturn(new TrainingType());
        trainerService.update("johndoe", updateDto);
        verify(trainerRepository, times(1)).getByUsername(anyString());
        verify(trainerRepository, times(1)).update(any(Trainer.class));
    }

    @Test
    void testToggleActive() {
        doNothing().when(userService).toggleActive(anyString(), anyBoolean());
        trainerService.toggleActive("johndoe", true);
        verify(userService, times(1)).toggleActive(anyString(), anyBoolean());
    }

    @Test
    void testGetById() {
        Trainer trainer = new Trainer();
        when(trainerRepository.getById(anyLong())).thenReturn(Optional.of(trainer));
        Optional<Trainer> result = trainerService.getById(1L);
        verify(trainerRepository, times(1)).getById(anyLong());
        assertTrue(result.isPresent());
    }

    @Test
    void testGetByIdNotFound() {
        when(trainerRepository.getById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> trainerService.getById(1L));
    }

    @Test
    void testGetUnassigned() {
        List<Trainer> trainers = new ArrayList<>();
        Trainer trainer = new Trainer();
        trainer.setUser(new User("John", "Doe"));
        trainer.setSpeciality(new TrainingType("Some Training"));
        trainers.add(trainer);
        when(trainerRepository.getUnassigned(anyString())).thenReturn(Optional.of(trainers));
        Optional<List<Trainer>> result = trainerService.getUnassigned("trainee");
        verify(trainerRepository, times(1)).getUnassigned(anyString());
        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
    }
}