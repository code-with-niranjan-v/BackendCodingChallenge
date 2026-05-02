package com.example.demo.service;

import com.example.demo.dto.RegisterDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    public RegisterDTO registerUser(RegisterDTO registerDTO){
        User user = UserMapper.registerDTOToUser(registerDTO);
        userRepo.save(user);
        return registerDTO;
    }

}
