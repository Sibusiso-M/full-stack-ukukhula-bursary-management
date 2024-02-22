package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmail(String email);
    Boolean userExists(String email);
}
