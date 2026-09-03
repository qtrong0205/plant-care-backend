package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.UserResponse;
import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody UserCreationRequest request
            ){
        return userService.register(request);
    }

    @GetMapping
    public ApiResponse<UserResponse> getUserProfile(
            @AuthenticationPrincipal Jwt jwt
            ) {
        return userService.getUserProfile(jwt);
    }
}
