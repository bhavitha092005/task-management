package com.bhavitha.taskmanager.controller;

import com.bhavitha.taskmanager.entity.User;
import com.bhavitha.taskmanager.exception.CustomException;
import com.bhavitha.taskmanager.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/profile")
    public User getProfile(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new CustomException("User not found"));

       
        user.setPassword(null);

        return user;
    }
}