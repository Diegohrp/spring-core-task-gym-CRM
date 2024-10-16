package com.diegohp.entity.user;

import com.diegohp.entity.training.TrainingType;

public class Trainer extends User {
    private TrainingType speciality;

    public Trainer(Long id, String firstName, String lastName, String username,
                   String password, boolean isActive, TrainingType speciality) {
        super(id, firstName, lastName, username, password, isActive);
        this.speciality = speciality;
    }

    public TrainingType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(TrainingType speciality) {
        this.speciality = speciality;
    }
}
