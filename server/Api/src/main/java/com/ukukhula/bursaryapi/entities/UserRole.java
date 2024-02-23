package com.ukukhula.bursaryapi.entities;

import lombok.Data;

@Data
public class UserRole {
    private int id;
    private String role;

    public UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }
}
