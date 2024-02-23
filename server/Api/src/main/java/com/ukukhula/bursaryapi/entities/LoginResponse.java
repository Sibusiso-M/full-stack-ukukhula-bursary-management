package com.ukukhula.bursaryapi.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String  accessToken;
}
