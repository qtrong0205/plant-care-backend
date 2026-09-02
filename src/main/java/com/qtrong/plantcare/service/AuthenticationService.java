package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.request.AuthenticationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.AuthenticationResponse;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request){
        if(!userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var user = userRepository.findByEmail(request.getEmail());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(authenticated)
                        .build())
                .code(200)
                .build();
    }
}
