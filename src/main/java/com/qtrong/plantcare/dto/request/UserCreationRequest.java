package com.qtrong.plantcare.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreationRequest {
    @Email(message = "INVALID_EMAIL")
    private String email;
    @Size(min = 6, max = 20, message = "INVALID_PASSWORD")
    private String password;
    private String name;
}
