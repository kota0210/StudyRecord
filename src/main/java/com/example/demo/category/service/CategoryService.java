package com.example.demo.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.user.entity.User;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepsitory;

    // コンストラクタでカテゴリリポジトリを注入
    public CategoryService(CategoryRepository categoryRepsitory){
        this.categoryRepsitory = categoryRepsitory;
    }

    // カテゴリの登録
    public void create(User user, String name){
        String trimedName = name.trim();
        if(trimedName.isEmpty()){
            throw new IllegalArgumentException("カテゴリ名を入力してください。");
        }

        if(categoryRepsitory.existsByUserIdAndName(user.getId(), trimedName)){
            throw new IllegalArgumentException("同じ名前のカテゴリは既に存在しています。");
            
        }

        Category category = Category.builder()
                                    .user(user)
                                    .name(trimedName)
                                    .build();
        categoryRepsitory.save(category);
    }

    // カテゴリの一覧表示
    public List<Category> findAll(){
        return categoryRepsitory.findAll();
    }

    // カテゴリ更新

    // カテゴリ削除

}
