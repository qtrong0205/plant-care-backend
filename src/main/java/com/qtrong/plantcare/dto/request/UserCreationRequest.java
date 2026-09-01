package com.qtrong.plantcare.dto.request;

import lombok.Getter;

import java.util.Date;

@Getter
public class UserCreationRequest {
    private String email;
    private String password;
    private String name;
    private Date created_at;
}
