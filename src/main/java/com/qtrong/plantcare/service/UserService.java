package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.UserResponse;
import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.mapper.UserMapper;
import com.qtrong.plantcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ApiResponse<UserResponse> register(
            UserCreationRequest request
    ){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ApiResponse.<UserResponse>builder()
                .code(201)
                .result(userMapper.toUserResponse(user))
                .build();
    }

    public ApiResponse<UserResponse> getUserProfile(Jwt jwt) {
        String userId = jwtService.extractUserId(jwt);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return ApiResponse.<UserResponse>builder()
                .code(200)
                .result(userMapper.toUserResponse(user))
                .build();
    }
}
