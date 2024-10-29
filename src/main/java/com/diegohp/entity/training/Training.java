package com.diegohp.entity.training;


import com.diegohp.entity.user.Trainee;
import com.diegohp.entity.user.Trainer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "training_type_id")
    private TrainingType type;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Integer duration;

    public Training() {

    }

    /*@JsonCreator
    public Training(@JsonProperty("traineeId") Long traineeId, @JsonProperty("trainerId") Long trainerId,
                    @JsonProperty("name") String name, @JsonProperty("type") TrainingType type,
                    @JsonProperty("date") Date date, @JsonProperty("duration") String duration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.duration = Duration.parse(duration);

    }

     */

    /*public Training(Training training) {
        this.traineeId = training.getTraineeId();
        this.trainerId = training.getTrainerId();
        this.name = training.getName();
        this.type = training.getType();
        this.date = training.getDate();
        this.duration = training.getDuration();
    }*/

    @Override
    public String toString() {
        return "Training={ trainerId: " + this.trainer + ", traineeId: " + this.trainee + ", name: " + this.name + ", type: " + this.type + ", date: " + this.date + ", duration: " + this.duration + " }";
    }

    /*

    public Long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Long traineeId) {
        this.traineeId = traineeId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingType getType() {
        return type;
    }

    public void setType(TrainingType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
