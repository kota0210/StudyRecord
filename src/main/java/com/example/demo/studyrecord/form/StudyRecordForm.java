package com.example.demo.studyrecord.form;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 学習記録の編集のためのフォームクラス
public class StudyRecordForm {
    
    // タイトル
    @NotBlank(message = "タイトルを入力してください。")
    @Size(max = 50, message = "50文字以内で入力してください。")
    private String title;

    // 内容
    @Size(max = 1000, message = "1000文字以内で入力してください。")
    private String content;

    // 学習日
    @NotBlank(message = "学習日を入力してください。")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate studyDate;

    // 学習時間
    @NotBlank(message = "学習時間を入力してください。")
    private Integer durationMinutes;

    // カテゴリID
    @NotBlank(message = "カテゴリを選択してください。")
    private Long categoryId;


    // タイトルのゲッターとセッター
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    // 内容のゲッターとセッター
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    // 学習日のゲッターとセッター
    public LocalDate getStudyDate(){
        return studyDate;
    }

    public void setStudyDate(LocalDate studyDate){
        this.studyDate = studyDate;
    }

    // 学習時間のゲッターとセッター
    public Integer getDurationMinutes(){
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes){
        this.durationMinutes = durationMinutes;
    }

    // カテゴリIDのゲッターとセッター
    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

}
