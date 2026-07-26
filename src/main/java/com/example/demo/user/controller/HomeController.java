package com.example.demo.user.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHome(Authentication authentication, Model model) {

        String email = authentication.getName();
        model.addAttribute("email", email);

        return "home";
    }
    
}
