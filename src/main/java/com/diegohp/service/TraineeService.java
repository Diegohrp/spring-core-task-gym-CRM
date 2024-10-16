package com.diegohp.service;

import com.diegohp.dao.TraineeDAO;
import com.diegohp.entity.user.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {
    @Autowired
    private TraineeDAO traineeDAO;

    public void createTrainee(Trainee trainee) {
        traineeDAO.create(trainee);
    }

    public Trainee getTrainee(Long id) {
        return traineeDAO.getById(id);
    }

    public void updateTrainee(Trainee trainee) {
        traineeDAO.update(trainee);
    }

    public void deleteTrainee(Long id) {
        traineeDAO.delete(id);
    }
}
