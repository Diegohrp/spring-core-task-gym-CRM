package com.diegohp.entity.user;

import com.diegohp.entity.training.Training;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trainees")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @OneToMany(mappedBy = "trainee")
    private List<Training> trainings;

    /*public Trainee(@JsonProperty("id") Long id, @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName, @JsonProperty("username") String username,
                   @JsonProperty("password") String password, @JsonProperty("isActive") boolean isActive,
                   @JsonProperty("dateOfBirth") Date birthday, @JsonProperty("address") String address) {
        //super(id, firstName, lastName, username, password, isActive);
        this.dateOfBirth = birthday;
        this.address = address;
    }
     */

    public Trainee(Date birthday, String address) {
        this.dateOfBirth = birthday;
        this.address = address;
    }

    public Trainee(Trainee trainee) {
        this.id = trainee.getId();
        this.trainings = trainee.trainings;
        this.user = new User(trainee.getUser());
        this.dateOfBirth = trainee.getDateOfBirth();
        this.address = trainee.getAddress();
    }

    public Trainee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
        }

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null) {
            this.address = address;
        }
    }

    @Override
    public String toString() {
        return "Trainee={ id: " + this.id + ", " + this.user + ", dateOfBirth: " + this.dateOfBirth + ", address: " + this.address + " }";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
