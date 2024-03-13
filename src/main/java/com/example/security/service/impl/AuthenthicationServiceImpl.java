package com.example.security.service.impl;

import com.example.security.dto.JWTAuthenticationResponse;
import com.example.security.dto.RefreshTokenRequestDTo;
import com.example.security.dto.SignInRequestDto;
import com.example.security.dto.SignUpRequetDto;
import com.example.security.entities.Role;
import com.example.security.entities.User;
import com.example.security.repositories.UserRepository;
import com.example.security.service.AuthenthicationService;
import com.example.security.service.JWTService;
import com.example.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenthicationServiceImpl implements AuthenthicationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    /**
     * @param signUpRequetDto 
     * @return
     */
    @Override
    public User signup(SignUpRequetDto signUpRequetDto) {
        User user=new User();
        user.setEmail(signUpRequetDto.getEmail());
        user.setFirstName(signUpRequetDto.getFirstName());
        user.setLastName(signUpRequetDto.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequetDto.getPassword()));
        return userRepository.save(user);
    }
    public JWTAuthenticationResponse signIn(SignInRequestDto signInDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (signInDto.getEmail(),signInDto.getPassword()));

        var user = userRepository.findByEmail(signInDto.getEmail())
        .orElseThrow(()-> new IllegalArgumentException("invalid username or password"));
          var jwt=jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
        System.out.println(jwt);
        System.out.println("login success");
        JWTAuthenticationResponse jwtAuthenticationResponse=new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return  jwtAuthenticationResponse;
    }
    public JWTAuthenticationResponse refreshToken(RefreshTokenRequestDTo refreshTokenRequestDTo){
        String userEmail=jwtService.extractUserName(refreshTokenRequestDTo.getToken());
        User user=userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDTo.getToken() ,user)){
            var jwt=jwtService.generateToken(user);

            JWTAuthenticationResponse jwtAuthenticationResponse=new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequestDTo.getToken());
            return  jwtAuthenticationResponse;
        }
return null;

    }
}
