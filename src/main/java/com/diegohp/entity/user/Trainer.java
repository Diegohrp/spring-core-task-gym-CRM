package com.diegohp.entity.user;

import com.diegohp.entity.training.TrainingType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Trainer extends User {
    private TrainingType speciality;

    @JsonCreator
    public Trainer(@JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName, @JsonProperty("username") String username,
                   @JsonProperty("password") String password, @JsonProperty("isActive") boolean isActive,
                   @JsonProperty("speciality") TrainingType speciality) {
        super(id, firstName, lastName, username, password, isActive);
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Trainer={ " + super.toString() + ", speciality: " + this.speciality.getName() + " }";
    }

    public TrainingType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(TrainingType speciality) {
        this.speciality = speciality;
    }

}
