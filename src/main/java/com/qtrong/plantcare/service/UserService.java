package com.qtrong.plantcare.service;

import com.qtrong.plantcare.dto.request.UserCreationRequest;
import com.qtrong.plantcare.dto.response.UserResponse;
import com.qtrong.plantcare.entity.User;
import com.qtrong.plantcare.mapper.UserMapper;
import com.qtrong.plantcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public boolean register(
            UserCreationRequest request
    ){
        if(userRepository.existsByEmail(request.getEmail())){
            return false;
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }
}
