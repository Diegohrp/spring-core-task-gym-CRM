package com.diegohp.service;

import com.diegohp.entity.user.User;
import com.diegohp.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

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

    public void updateUser(User user, User data) {
        if (data.getFirstName() != null)
            user.setFirstName(data.getFirstName());

        if (data.getLastName() != null)
            user.setLastName(data.getLastName());

        if (data.getPassword() != null)
            user.setPassword(data.getPassword());

        user.setIsActive(data.getIsActive());
    }

    public boolean usernameMustChange(User original, User updated) {
        return !updated.getFirstName().equals(original.getFirstName())
                || !updated.getLastName().equals(original.getLastName());
    }
}

