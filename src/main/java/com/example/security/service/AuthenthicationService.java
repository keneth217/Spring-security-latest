package com.example.security.service;

import com.example.security.dto.JWTAuthenticationResponse;
import com.example.security.dto.RefreshTokenRequestDTo;
import com.example.security.dto.SignInRequestDto;
import com.example.security.dto.SignUpRequetDto;
import com.example.security.entities.User;

public interface AuthenthicationService {
    User signup(SignUpRequetDto signUpRequetDto);
    JWTAuthenticationResponse signIn(SignInRequestDto signInDto);
    JWTAuthenticationResponse refreshToken(RefreshTokenRequestDTo refreshTokenRequestDTo);

}
