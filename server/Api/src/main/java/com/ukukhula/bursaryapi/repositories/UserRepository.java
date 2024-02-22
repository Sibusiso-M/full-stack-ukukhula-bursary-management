package com.ukukhula.bursaryapi.repositories;

import com.ukukhula.bursaryapi.entities.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByEmail(String email);
    Boolean userExists(String email);
}
