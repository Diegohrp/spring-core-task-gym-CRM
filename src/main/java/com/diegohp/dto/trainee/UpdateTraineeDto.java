package com.diegohp.dto.trainee;

import com.diegohp.dto.user.UpdateUserDto;

import java.util.Date;

public class UpdateTraineeDto {
    private UpdateUserDto userDto;
    private Date dateOfBirth;
    private String address;

    public UpdateTraineeDto(UpdateUserDto userDto, Date dateOfBirth, String address) {
        this.userDto = userDto;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public UpdateUserDto getUserDto() {
        return userDto;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }
}


