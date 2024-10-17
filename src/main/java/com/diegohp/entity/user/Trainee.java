package com.diegohp.entity.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Trainee extends User {
    private Date dateOfBirth;
    private String address;

    @JsonCreator
    public Trainee(@JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName, @JsonProperty("username") String username,
                   @JsonProperty("password") String password, @JsonProperty("isActive") boolean isActive,
                   @JsonProperty("dateOfBirth") Date birthday, @JsonProperty("address") String address) {
        super(id, firstName, lastName, username, password, isActive);
        this.dateOfBirth = birthday;
        this.address = address;
    }

    public Trainee(String firstName, String lastName, Date birthday, String address) {
        super(firstName, lastName);
        this.dateOfBirth = birthday;
        this.address = address;
    }

    public Trainee(Trainee trainee) {
        super(trainee.getId(), trainee.getFirstName(), trainee.getLastName(), trainee.getUsername(), trainee.getPassword(), trainee.getIsActive());
        this.dateOfBirth = trainee.getDateOfBirth();
        this.address = trainee.getAddress();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Trainee={ " + super.toString() + ", dateOfBirth: " + this.dateOfBirth + ", address: " + this.address + " }";
    }
}
