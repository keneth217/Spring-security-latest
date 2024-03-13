package com.example.security.controller;

import com.example.security.dto.JWTAuthenticationResponse;
import com.example.security.dto.RefreshTokenRequestDTo;
import com.example.security.dto.SignInRequestDto;
import com.example.security.dto.SignUpRequetDto;
import com.example.security.entities.User;
import com.example.security.service.AuthenthicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenthicationService authenthicationService;


    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequetDto signUpRequetDto){
        return ResponseEntity.ok(authenthicationService.signup(signUpRequetDto));
    }
    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SignInRequestDto signInRequestDto){
        return  ResponseEntity.ok(authenthicationService.signIn(signInRequestDto));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequestDTo refreshTokenRequestDTo){
        return  ResponseEntity.ok(authenthicationService.refreshToken(refreshTokenRequestDTo));
    }
}
