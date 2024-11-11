package com.diegohp.entity.training.enums;

public enum TrainingTypes {
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

    TrainingTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
