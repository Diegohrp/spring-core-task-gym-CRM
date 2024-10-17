package com.diegohp.service;

import com.diegohp.entity.user.User;


public abstract class UserService {

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

