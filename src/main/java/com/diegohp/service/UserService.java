package com.diegohp.service;

import com.diegohp.dto.user.UpdateUserDto;
import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository repository;
    private Logger logger;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Transactional
    public User create(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        user.setUsername(this.generateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(this.generatePassword());
        repository.create(user);
        return user;
    }

    public void updateUser(User user, UpdateUserDto userDto) {
        String prevFirstName = user.getFirstName();
        String prevLastName = user.getLastName();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getNewPassword());
        user.setIsActive(userDto.getActive());
        if (usernameMustChange(user, prevFirstName, prevLastName)) {
            user.setUsername(generateUsername(user.getFirstName(), user.getLastName()));
        }
        repository.update(user);
    }

    @Transactional
    public void toggleActive(String username, Boolean active) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isEmpty()) {
            logger.error("The user you want to change status does not exist");
            return;
        }
        user.get().setIsActive(active);
        repository.update(user.get());
        logger.info("User {} has change their isActive status to: {}", username, active);
    }


    private Boolean userNameExists(String username) {
        return repository.findByUsername(username).isPresent();
    }

    private String generateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;
        String username = baseUsername;
        int counter = 1;
        while (this.userNameExists(username)) {
            counter++;
            username = baseUsername + counter;
        }
        logger.info("Username has been generated");
        return username;
    }

    private String generatePassword() {
        StringBuilder sb = new StringBuilder();
        int asciiStart = 33;
        int asciiEnd = 126;
        for (int i = 0; i < 10; i++) {
            char character = (char) Math.floor(Math.random() * (asciiEnd - asciiStart) + asciiStart);
            sb.append(character);
        }
        logger.info("Password has been generated");

        return sb.toString();
    }


    public boolean usernameMustChange(User updated, String prevFirstName, String prevLastName) {
        return !updated.getFirstName().equals(prevFirstName)
                || !updated.getLastName().equals(prevLastName);
    }
}

