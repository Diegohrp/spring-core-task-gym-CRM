package com.diegohp.dto;

import java.util.Date;

public class CreateTraineeDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;

    public CreateTraineeDto(String firstName, String lastName, Date dateOfBirth, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }
}
