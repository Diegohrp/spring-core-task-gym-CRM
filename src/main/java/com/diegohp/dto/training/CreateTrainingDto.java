package com.diegohp.dto.training;

import java.util.Date;

public class CreateTrainingDto {
    private final Long traineeId;
    private final Long trainerId;
    private final String name;
    private final Date date;
    private final Integer duration;

    public CreateTrainingDto(Long traineeId, Long trainerId, String name, Date date, Integer duration) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.name = name;
        this.date = date;
        this.duration = duration;
    }

    public Long getTraineeId() {
        return traineeId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDuration() {
        return duration;
    }
}
