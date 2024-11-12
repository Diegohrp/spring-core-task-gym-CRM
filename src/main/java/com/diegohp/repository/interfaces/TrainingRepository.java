package com.diegohp.repository.interfaces;

import com.diegohp.entity.training.Training;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository {
    void create(Training training);

    Optional<List<Training>> getByCriteria(String trainee, Date fromDate, Date toDate, String trainerName, Long type);

    Optional<List<Training>> getByCriteria(String trainer, Date fromDate, Date toDate, String traineeName);
}
