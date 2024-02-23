package com.ukukhula.bursaryapi.services;

import com.ukukhula.bursaryapi.entities.UserRole;

import java.util.Optional;

public interface UserRoleService {
    String getByRoleId(int roleId);
}
