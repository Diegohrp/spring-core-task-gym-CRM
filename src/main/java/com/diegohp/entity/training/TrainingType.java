package com.diegohp.entity.training;

import com.diegohp.entity.user.Trainer;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "training_types")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "speciality")
    private List<Trainer> trainers;

    @OneToMany(mappedBy = "type")
    private List<Training> trainings;

    public TrainingType() {

    }

}
