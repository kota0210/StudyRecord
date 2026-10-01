package com.example.demo.studyrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.form.StudyRecordForm;
import com.example.demo.studyrecord.repository.StudyRecordRepository;
import com.example.demo.user.entity.User;

import jakarta.transaction.Transactional;


// 学習記録の登録と一覧表示のための機能
@Service
@Transactional
public class StudyRecordService {
    private final StudyRecordRepository studyRecordRepository;
    private final CategoryRepository categoryRepository;


    // コンストラクタで学習記録リポジトリとパスワードエンコーダー（パスワードをハッシュ化する）を注入
    public StudyRecordService(StudyRecordRepository studyRecordRepository, CategoryRepository categoryRepository){
        this.studyRecordRepository = studyRecordRepository;
        this.categoryRepository = categoryRepository;
    }

    // 学習記録の登録
    public void save(StudyRecord studyRecord, User user, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("カテゴリが見つかりません"));
        studyRecord.setCategory(category);
        studyRecord.setUser(user);
        studyRecordRepository.save(studyRecord);
    }

    // 学習記録の一覧表示
    public List<StudyRecord> findAll() {
        return studyRecordRepository.findAll();
    }

    // 詳細取得


    // 編集画面の表示
    public StudyRecord findByIdAndUserId(Long userId, Long id) {
        return studyRecordRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new IllegalArgumentException("学習記録はまだ登録されていません。"));
    }

    // 更新・・・学習記録編集画面にて編集した内容をIDにて更新する
    public void update(Long id, Long userId, StudyRecordForm form) {
    StudyRecord studyRecord = studyRecordRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new IllegalArgumentException("学習記録が見つかりません。"));

    Category category = categoryRepository.findByIdAndUser_Id(form.getCategoryId(), userId)
            .orElseThrow(() -> new IllegalArgumentException("カテゴリが見つかりません。"));

    studyRecord.setTitle(form.getTitle());
    studyRecord.setContent(form.getContent());
    studyRecord.setStudyDate(form.getStudyDate());
    studyRecord.setDurationMinutes(form.getDurationMinutes());
    studyRecord.setCategory(category);
}
    
    // 削除
    public void delete(@PathVariable Long id, Model model) {
        studyRecordRepository.deleteById(id);
         model.addAttribute("message", "学習記録が削除されました。");
    }
    
    // カテゴリー別の学習記録の取得
    
}
