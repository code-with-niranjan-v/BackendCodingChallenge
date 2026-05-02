package com.example.demo.mapper;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.model.User;

public class UserMapper {

    public static User registerDTOToUser(RegisterDTO registerDTO){
        User user  = new User();
        user.setName(registerDTO.getName());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        return user;
    }

}
