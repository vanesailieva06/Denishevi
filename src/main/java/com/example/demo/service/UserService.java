package com.example.demo.service;

import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.model.entity.User;

public interface UserService {
    User findByUsername(String username);

    void registerUser(UserRegisterDto userRegisterDto);
}
