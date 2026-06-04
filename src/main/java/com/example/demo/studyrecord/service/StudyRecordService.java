package com.example.demo.studyrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.repository.StudyRecordRepository;

// 学習記録の登録と一覧表示のための機能
@Service
public class StudyRecordService {
    private final StudyRecordRepository studyRecordRepository;
    
    // コンストラクタで学習記録リポジトリを注入
    public StudyRecordService(StudyRecordRepository studyRecordRepository){
        this.studyRecordRepository = studyRecordRepository;
    }

    // 学習記録の登録
    public void save(StudyRecord studyRecord) {
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
    public StudyRecord update(Long id, StudyRecord editedRecord, Long userId) {
        StudyRecord studyRecord = studyRecordRepository.findByIdAndUserId(id, userId)
            .orElseThrow(() -> new IllegalArgumentException("学習記録が見つかりません"));

        studyRecord.setStudyDate(editedRecord.getStudyDate());
        studyRecord.setDurationMinutes(editedRecord.getDurationMinutes());
        studyRecord.setContent(editedRecord.getContent());
        studyRecord.setMemo(editedRecord.getMemo());

        return studyRecordRepository.save(studyRecord);
    }
    
    // 削除
    public void delete(@PathVariable Long id, Model model) {
        studyRecordRepository.deleteById(id);
         model.addAttribute("message", "学習記録が削除されました。");
    }
    
    // カテゴリー別の学習記録の取得
    
}
