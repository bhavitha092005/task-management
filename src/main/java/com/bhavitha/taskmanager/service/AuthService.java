package com.bhavitha.taskmanager.service;

import com.bhavitha.taskmanager.dto.LoginRequest;
import com.bhavitha.taskmanager.dto.RegisterRequest;
import com.bhavitha.taskmanager.entity.User;
import com.bhavitha.taskmanager.exception.CustomException;
import com.bhavitha.taskmanager.repository.UserRepository;
import com.bhavitha.taskmanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getId());
    }
}