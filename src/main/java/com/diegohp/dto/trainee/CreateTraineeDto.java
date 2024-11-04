package com.diegohp.dto.trainee;

import com.diegohp.dto.user.CreateUserDto;

import java.util.Date;


public class CreateTraineeDto {
    private CreateUserDto userDto;
    private Date dateOfBirth;
    private String address;

    public CreateTraineeDto(CreateUserDto userDto, Date dateOfBirth, String address) {
        this.userDto = userDto;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public CreateUserDto getUserDto() {
        return userDto;
    }
}
