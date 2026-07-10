package com.example.demo.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

public class UserRegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // コンストラクタで学習記録リポジトリとパスワードエンコーダー（パスワードをハッシュ化する）を注入
    public UserRegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email, String password){

        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("メールアドレスを入力してください。");
        }
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("パスワードを入力してください。");
        }

        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("このメールアドレスは既に登録されています。");
        }
        
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        userRepository.save(user);
    }

}
