package com.example.demo.auth.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.auth.service.AuthService;
import com.example.demo.user.entity.User;

@Controller
public class AuthController {
    @Autowired
    private AuthService service;

    @GetMapping("/register")
    public String register(@RequestParam String name,
    @RequestParam int employees,
    @RequestParam String establishmentDate,User user){
        service.insertSample(user);
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
