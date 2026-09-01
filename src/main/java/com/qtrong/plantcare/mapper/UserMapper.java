package com.qtrong.plantcare.mapper;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.entity.User;

import java.util.Date;

public class UserMapper {
    public User toUser(UserCreationRequest request){
        var user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setCreated_at(new Date());
        return user;
    }
}
