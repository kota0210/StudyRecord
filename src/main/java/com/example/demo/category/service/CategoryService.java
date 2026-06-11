package com.example.demo.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.user.entity.User;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // コンストラクタでカテゴリリポジトリを注入
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    // カテゴリの登録
    public void create(User user, String name){
        String trimedName = name.trim();
        if(trimedName.isEmpty()){
            throw new IllegalArgumentException("カテゴリ名を入力してください。");
        }

        if(categoryRepository.existsByUserIdAndName(user.getId(), trimedName)){
            throw new IllegalArgumentException("同じ名前のカテゴリは既に存在しています。");
            
        }

        Category category = Category.builder()
                                    .user(user)
                                    .name(trimedName)
                                    .build();
        categoryRepository.save(category);
    }

    // カテゴリの一覧表示・・・ユーザーIDからカテゴを絞り込む
    public List<Category> findAllByUserId(Long userId){
        return categoryRepository.findAllByUserIdOrderByCreatedAtAsc(userId);
    }

    // カテゴリ更新
    public void update(Long categoryId, Long userId, String name){
        String trimmedName = name.trim();
        if(trimmedName.isEmpty()){
            throw new IllegalArgumentException("カテゴリ名を入力してください。");
        }

        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                                .orElseThrow(() -> new IllegalArgumentException("カテゴリが見つかりません。"));

        if(!category.getName().equals(trimmedName) && categoryRepository.existsByUserIdAndName(userId,trimmedName)){
            throw new IllegalArgumentException("同じカテゴリ名で既に登録されています。");
        }

        category.updateName(trimmedName);

    }
    // カテゴリ削除
    public void delete(long categoryId, Long userId){
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId)
                                .orElseThrow(() -> new IllegalArgumentException("カテゴリが見つかりません。"));
        categoryRepository.delete(category);
    }

}
