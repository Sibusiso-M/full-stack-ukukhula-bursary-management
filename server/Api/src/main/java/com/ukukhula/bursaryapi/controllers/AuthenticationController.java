package com.ukukhula.bursaryapi.controllers;

import com.ukukhula.bursaryapi.entities.LoginResponse;
import com.ukukhula.bursaryapi.entities.User;
import com.ukukhula.bursaryapi.security.JwtIssuer;

import com.ukukhula.bursaryapi.services.UserRoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationController {

    private final JwtIssuer jwtIssuer;
    private final UserRoleService userRoleService;

    public AuthenticationController(JwtIssuer jwtIssuer, UserRoleService userRoleService) {
        this.jwtIssuer = jwtIssuer;
        this.userRoleService = userRoleService;
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated User userDetails) {
//        System.out.println(userDetails);

        var token = jwtIssuer.issue(1, userDetails.getFirstName(),
                List.of("USER"));
        return LoginResponse.builder().accessToken(token)
                .build();
    }
}
