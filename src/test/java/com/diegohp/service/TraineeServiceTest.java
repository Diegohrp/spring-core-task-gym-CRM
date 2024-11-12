package com.diegohp.service;

import com.diegohp.dto.trainee.CreateTraineeDto;
import com.diegohp.dto.trainee.UpdateTraineeDto;
import com.diegohp.dto.user.CreateUserDto;
import com.diegohp.dto.user.UpdateUserDto;
import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.TraineeRepository;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {
    @Mock
    private TraineeRepository traineeRepository;
    @Mock
    private UserService userService;
    @Mock
    private Logger logger;
    @InjectMocks
    private TraineeService traineeService;
    private CreateTraineeDto traineeDto;

    @BeforeEach
    void setUp() {
        CreateUserDto userDto = new CreateUserDto("John", "Doe");
        traineeDto = new CreateTraineeDto(userDto, new Date(), "123 Main St");
        traineeService.setLogger(logger);
    }

    @Test
    void testCreate() {
        when(userService.create(anyString(), anyString())).thenReturn(new User());
        doNothing().when(traineeRepository).create(any(Trainee.class));
        String password = traineeService.create(traineeDto);
        verify(userService, times(1)).create(anyString(), anyString());
        verify(traineeRepository, times(1)).create(any(Trainee.class));
    }

    @Test
    void testGetById() {
        Trainee trainee = new Trainee(new Date(), "123 Main St");
        when(traineeRepository.getById(anyLong())).thenReturn(Optional.of(trainee));
        Optional<Trainee> result = traineeService.getById(1L);
        verify(traineeRepository, times(1)).getById(anyLong());
        assertTrue(result.isPresent());
    }

    @Test
    void testGetByIdNotFound() {
        when(traineeRepository.getById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> traineeService.getById(1L));
    }

    @Test
    void testGetByUsername() {
        Trainee trainee = new Trainee(new Date(), "123 Main St");
        when(traineeRepository.getByUsername(anyString())).thenReturn(Optional.of(trainee));
        Optional<Trainee> result = traineeService.getByUsername("johndoe");
        verify(traineeRepository, times(1)).getByUsername(anyString());
        assertTrue(result.isPresent());
    }

    @Test
    void testUpdate() {
        Trainee trainee = new Trainee(new Date(), "123 Main St");
        trainee.setUser(new User("John", "Doe"));
        UpdateTraineeDto updateDto = new UpdateTraineeDto(new UpdateUserDto("Jane", "Doe", null, null), new Date(), "456 Elm St");
        when(traineeRepository.getByUsername(anyString())).thenReturn(Optional.of(trainee));
        traineeService.update("John.Doe", updateDto);
        verify(traineeRepository, times(1)).getByUsername(anyString());
        verify(traineeRepository, times(1)).update(any(Trainee.class));
    }

    @Test
    void testToggleActive() {
        doNothing().when(userService).toggleActive(anyString(), anyBoolean());
        traineeService.toggleActive("johndoe", true);
        verify(userService, times(1)).toggleActive(anyString(), anyBoolean());
    }

    @Test
    void testDelete() {
        Trainee trainee = new Trainee(new Date(), "123 Main St");
        when(traineeRepository.getByUsername(anyString())).thenReturn(Optional.of(trainee));
        doNothing().when(traineeRepository).delete(any(Trainee.class));
        traineeService.delete("johndoe");
        verify(traineeRepository, times(1)).getByUsername(anyString());
        verify(traineeRepository, times(1)).delete(any(Trainee.class));
    }
}