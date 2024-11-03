package com.diegohp.repository.interfaces;

import com.diegohp.entity.user.User;

import java.util.Optional;

public interface UserRepository {
    void create(User user);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    void delete(User user);

    void update(User user);

}
