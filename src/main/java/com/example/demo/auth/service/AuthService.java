package com.example.demo.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    public void insertSample(User user) {
    repository.save(user);
}
}
