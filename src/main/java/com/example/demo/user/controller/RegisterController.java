package com.example.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.service.UserRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
private final UserRegisterService userRegisterService;

 public RegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    



    
}
