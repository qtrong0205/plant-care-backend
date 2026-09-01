package com.qtrong.plantcare.dto.request;

import lombok.Getter;

@Getter
public class UserCreationRequest {
    private String email;
    private String password;
    private String name;
}
