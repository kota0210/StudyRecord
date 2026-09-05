package com.example.demo.category.service;

import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.user.entity.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks 
    private CategoryService categoryService;

    @Test
    void カテゴリが空だとエラーになる() {
        User user = new User();
        user.setId(1L);

        assertThatThrownBy(() -> categoryService.create(user, " "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("カテゴリ名を入力してください。");

        verify(categoryRepository, never()).save(any());
    }

        
    }