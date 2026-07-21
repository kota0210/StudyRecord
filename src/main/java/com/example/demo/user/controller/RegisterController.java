package com.example.demo.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.user.service.UserRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
private final UserRegisterService userRegisterService;

 public RegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    // 登録フォームの表示
    @GetMapping("/register")
    public String showRegisterForm(){
        return "register";
    }

    // 登録処理
    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, Model model){

        // 確認用パスワードとの一致を確認
        if(!password.equals(confirmPassword)){
            model.addAttribute("registerError", "登録したパスワードと一致しません。");
            model.addAttribute("email", email);
            return "register";
        }

        // ユーザーを登録
        try{
            userRegisterService.registerUser(email, password);
            return "redirect:/login?registerSuccess";

        }catch(IllegalArgumentException e){
            model.addAttribute("registerError", e.getMessage());
            model.addAttribute("email", email);
            return "register";
        }
    }



    
}
