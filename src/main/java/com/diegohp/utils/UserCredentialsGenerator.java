package com.diegohp.utils;

import com.diegohp.entity.user.User;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialsGenerator {
    public String generateUsername(String firstName, String lastName, Function<String, Boolean> usernameExists) {
        String baseUsername = firstName + "." + lastName;
        String username = baseUsername;
        int counter = 1;
        while (usernameExists.apply(username)) {
            counter++;
            username = baseUsername + counter;
        }

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
        return sb.toString();
    }

    public void assignCredentials(User user, Function<String, Boolean> usernameExists) {
        user.setUsername(generateUsername(user.getFirstName(), user.getLastName(), usernameExists));
        user.setPassword(generatePassword());
    }
}
