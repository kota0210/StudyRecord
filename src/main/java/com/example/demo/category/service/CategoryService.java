package com.example.demo.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepsitory;

    // コンストラクタでカテゴリリポジトリを注入
    public CategoryService(CategoryRepository categoryRepsitory){
        this.categoryRepsitory = categoryRepsitory;
    }

    // カテゴリの登録
    public void save(Category category){
        categoryRepsitory.save(category);
    }

    // カテゴリの一覧表示
    public List<Category> findAll(){
        return categoryRepsitory.findAll();
    }

    // カテゴリ更新

    // カテゴリ削除

}
