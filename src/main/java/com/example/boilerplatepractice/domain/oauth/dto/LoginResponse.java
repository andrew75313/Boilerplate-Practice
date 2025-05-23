package com.example.boilerplatepractice.domain.oauth.dto;

import com.example.boilerplatepractice.domain.users.entity.User;
import lombok.Getter;

@Getter
public class LoginResponse {
    private User user;
    private String accessToken;
    private String refreshToken;

    public LoginResponse(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
