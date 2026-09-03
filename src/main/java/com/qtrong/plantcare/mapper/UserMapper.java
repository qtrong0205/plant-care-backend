package com.qtrong.plantcare.mapper;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.dto.response.UserResponse;
import com.qtrong.plantcare.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {
    public User toUser(UserCreationRequest request){
        var user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setCreated_at(new Date());
        return user;
    }

    public UserResponse toUserResponse(User user){
        var userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setCreated_at(user.getCreated_at());
        return userResponse;
    }
}
