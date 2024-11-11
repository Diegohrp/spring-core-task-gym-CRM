package com.diegohp.dto.trainer;

import com.diegohp.dto.user.CreateUserDto;


public class CreateTrainerDto {
    private final CreateUserDto userDto;
    private final Long speciality;

    public CreateTrainerDto(CreateUserDto user, Long speciality) {
        this.userDto = user;
        this.speciality = speciality;
    }

    public CreateUserDto getUserDto() {
        return userDto;
    }

    public Long getSpeciality() {
        return speciality;
    }
}
