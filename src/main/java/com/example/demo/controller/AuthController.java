package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.util.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return ResponseEntity.ok(new ApiResponse<>(200,"Login Successfull!", Map.of("token",jwtUtil.generateToken(loginDTO.getEmail()))));
        }else {
            return ResponseEntity.badRequest().body(new ApiResponse<>(400,"Login Failed",""));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterDTO registerDTO){
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        return ResponseEntity.ok(new ApiResponse<>(200,"User Registered!",authService.registerUser(registerDTO)));
    }

}
