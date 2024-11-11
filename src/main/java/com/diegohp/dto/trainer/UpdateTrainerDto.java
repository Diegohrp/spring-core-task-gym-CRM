package com.diegohp.dto.trainer;

import com.diegohp.dto.user.UpdateUserDto;

public class UpdateTrainerDto {
    private final UpdateUserDto userDto;
    private final Long speciality;

    public UpdateTrainerDto(UpdateUserDto userDto, Long speciality) {
        this.userDto = userDto;
        this.speciality = speciality;
    }

    public UpdateUserDto getUserDto() {
        return userDto;
    }

    public Long getSpeciality() {
        return speciality;
    }
}
