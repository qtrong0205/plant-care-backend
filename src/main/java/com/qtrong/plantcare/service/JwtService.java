package com.qtrong.plantcare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final NimbusJwtDecoder jwtDecoder;

    public String extractUserId(Jwt jwt){
        return jwt.getSubject();
    }
}
