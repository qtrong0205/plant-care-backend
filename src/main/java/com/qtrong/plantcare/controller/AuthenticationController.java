package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.AuthenticationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.AuthenticationResponse;
import com.qtrong.plantcare.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest request
            ){
        return authenticationService.authenticate(request);
    }
}
