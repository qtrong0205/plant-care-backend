package com.qtrong.plantcare.service;

import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;

    public String extractUserId(Jwt jwt){
        return jwt.getSubject();
    }

    public User extractUser(Jwt jwt){
        String userId = extractUserId(jwt);

        return userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
