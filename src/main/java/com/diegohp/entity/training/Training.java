package com.diegohp.entity.training;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.time.Duration;


public class Training {
    private Long traineeId;
    private Long trainerId;
    private String name;
    private TrainingType type;
    private Date date;
    private Duration duration;

    @JsonCreator
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

    @Override
    public String toString() {
        return "Training={ trainerId: " + this.trainerId + ", traineeId: " + this.traineeId + ", name: " + this.name + ", type: " + this.type.getName() + ", date: " + this.date + ", duration: " + this.duration + " }";
    }

    public long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(long traineeId) {
        this.traineeId = traineeId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
