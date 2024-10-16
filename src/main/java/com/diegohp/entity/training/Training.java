package com.diegohp.entity.training;

import java.sql.Date;
import java.time.Duration;

public class Training {
    private Long TraineeId;
    private Long TrainerId;
    private String name;
    private TrainingType type;
    private Date date;
    private Duration duration;

    public Training(Long traineeId, Long trainerId, String name, TrainingType type, Date date, Duration duration) {
        TraineeId = traineeId;
        TrainerId = trainerId;
        this.name = name;
        this.type = type;
        this.date = date;
        this.duration = duration;
    }

    public long getTraineeId() {
        return TraineeId;
    }

    public void setTraineeId(long traineeId) {
        TraineeId = traineeId;
    }

    public long getTrainerId() {
        return TrainerId;
    }

    public void setTrainerId(long trainerId) {
        TrainerId = trainerId;
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
