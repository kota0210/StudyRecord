package com.example.demo.studyrecord.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.repository.StudyRecordRepository;

// 学習記録の登録と一覧表示のための機能
@Service
public class StudyRecordService {
    private final StudyRecordRepository studyRecordRepository;
    
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
    
}
