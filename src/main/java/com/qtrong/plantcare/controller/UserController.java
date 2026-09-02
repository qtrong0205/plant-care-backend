package com.qtrong.plantcare.controller;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public User register(
            @Valid @RequestBody UserCreationRequest request
            ){
        return userService.register(request);
    }

    @GetMapping
    public String test() {
        return "UserController is working";
    }
}
