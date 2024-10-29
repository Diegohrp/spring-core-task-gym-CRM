package com.diegohp.entity.training.enums;

public enum TrainingType {
    CARDIO("Cardio"),
    WEIGHTLIFTING("Weightlifting"),
    STRENGTH("Strength"),
    POWERLIFTING("Powerlifting"),
    BODYBUILDING("Bodybuilding"),
    PILATES("Pilates"),
    CALISTHENICS("Calisthenics"),
    BOXING("Boxing"),
    CYCLING("Cycling");


    private final String name;

    TrainingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
