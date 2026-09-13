package com.example.demo.category.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

// カテゴリ編集用のフォームクラス
@Getter
@Setter
public class CategoryForm {
    //カテゴリ名
    @NotBlank (message = "タイトルを入力してください。")
    private String name;
    
    
}
