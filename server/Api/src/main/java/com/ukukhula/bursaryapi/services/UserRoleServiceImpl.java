package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;


@Service
public class UserRoleServiceImpl implements UserRoleService{

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public String getByRoleId(int roleId) {
        return userRoleRepository.getByRoleId(roleId);
    }
}
