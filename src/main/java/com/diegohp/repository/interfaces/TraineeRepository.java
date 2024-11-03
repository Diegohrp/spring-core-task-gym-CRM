package com.diegohp.repository.interfaces;

import com.diegohp.entity.user.Trainee;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TraineeRepository {
    void create(Trainee trainee);

    Optional<Trainee> getById(Long id);

    void update(Trainee trainee);

    void delete(Trainee trainee);
}
