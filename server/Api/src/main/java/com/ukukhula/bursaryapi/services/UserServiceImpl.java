package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.User;
import com.ukukhula.bursaryapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public Boolean userExists(String email) {
        return userRepository.userExists(email);
    }
}
