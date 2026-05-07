package com.example.demo.studyrecord.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// 学習記録登録時のDTO
public class StudyRecordResisterRequest {

    // タイトル
    @NotBlank(message = "タイトルを入力してください。")
    @Size(max = 50, message = "50文字以内で入力してください。")
    private String title;

    // 内容
    @Size(max = 1000, message = "1000文字以内で入力してください。")
    private String Content;

    // 学習日
    @NotBlank(message = "学習日を入力してください。")
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate studyDate;

    // 学習時間
    @NotBlank(message = "学習時間を入力してください。")
    private Integer durationMinutes;

    // カテゴリ
    @NotBlank(message = "カテゴリを入力してください。")
    private String category;
}
