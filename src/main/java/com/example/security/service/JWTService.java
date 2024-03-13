package com.example.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String extractUserName(String jwt);
   boolean isTokenValid(String token, UserDetails userDetails);
     String generateToken(UserDetails userDetails);
  String generateRefreshToken(Map<String,Object> extraClaims , UserDetails userDetails);
}
