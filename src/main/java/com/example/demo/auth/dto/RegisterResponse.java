package com.example.demo.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// ユーザー登録したときデータを受け取る
@Getter
@AllArgsConstructor
public class RegisterResponse {
    private Long id;
    private String name;
    private String email;
}
