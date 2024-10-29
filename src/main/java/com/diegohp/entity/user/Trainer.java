package com.diegohp.entity.user;


import com.diegohp.entity.training.Training;
import com.diegohp.entity.training.TrainingType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "speciality")
    private TrainingType speciality;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "trainer")
    private List<Training> trainings;

    @JsonCreator
    public Trainer(@JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName, @JsonProperty("username") String username,
                   @JsonProperty("password") String password, @JsonProperty("isActive") boolean isActive,
                   @JsonProperty("speciality") TrainingType speciality) {
        //super(id, firstName, lastName, username, password, isActive);
        this.speciality = speciality;
    }

    public Trainer(Trainer trainer) {
        //super(trainer.getId(), trainer.getFirstName(), trainer.getLastName(), trainer.getUsername(), trainer.getPassword(), trainer.getIsActive());
        this.speciality = trainer.getSpeciality();
    }

    public Trainer(String firstName, String lastName, TrainingType speciality) {
        //super(firstName, lastName);
        this.speciality = speciality;
    }

    public Trainer() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Trainer={ " + super.toString() + ", speciality: " + this.speciality + " }";
    }

    public TrainingType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(TrainingType speciality) {
        this.speciality = speciality;
    }

}
