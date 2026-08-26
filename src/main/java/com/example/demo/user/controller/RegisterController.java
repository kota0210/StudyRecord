package com.example.demo.user.controller;

import com.example.demo.user.service.UserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRegisterService userRegisterService;

    public RegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    // 登録フォームの表示
    @GetMapping
    public String showRegisterForm() {
        return "UserRegister";
    }

    // 登録処理
    @PostMapping
    public String registerUser(
            @RequestParam String email,
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model
    ) {
        // // 確認用パスワードとの一致を確認
        // if (!password.equals(confirmPassword)) {
        //     model.addAttribute("registerError", "入力した2つのパスワードが一致しません。");
        //     model.addAttribute("email", email);
        //     return "UserRegister";
        // }

        // try {
        //     userRegisterService.registerUser(email, password);
        //     return "redirect:/login?registered";
        // } catch (IllegalArgumentException e) {
        //     model.addAttribute("registerError", e.getMessage());
        //     model.addAttribute("email", email);
        //     return "UserRegister";
        // }

        System.out.println("===== /register POST に到達しました =====");
        System.out.println("email = " + email);

        try {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("registerError", "入力した2つのパスワードが一致しません。");
                model.addAttribute("name", name);
                model.addAttribute("email", email);
                return "UserRegister";
            }

            userRegisterService.registerUser(name,email, password);

            System.out.println("===== ユーザー登録成功 =====");

            return "redirect:/login?registered";

        } catch (Exception e) {
            System.out.println("===== ユーザー登録で例外発生 =====");
            e.printStackTrace();

            model.addAttribute("registerError", "登録処理でエラーが発生しました: " + e.getClass().getSimpleName() + " / " + e.getMessage());
            model.addAttribute("name", name);
            model.addAttribute("email", email);

            return "UserRegister";
        }
    }
}