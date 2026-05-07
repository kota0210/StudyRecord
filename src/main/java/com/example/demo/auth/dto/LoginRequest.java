package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// ログイン用のDTO
public class LoginRequest {

    // ログイン時のメールアドレスの入力
    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスの形式が正しくありません。")
    private String email;

    // ログイン時のパスワードの入力
    @NotBlank(message = "パスワードを入力してください。")
    private String password;
    
}
