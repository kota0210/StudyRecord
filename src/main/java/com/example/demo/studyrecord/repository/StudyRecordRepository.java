package com.example.demo.studyrecord.repository;

import com.example.demo.studyrecord.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
    List<StudyRecord> findAllByUserIdOrderByStudyDateDescCreatedAtDesc(Long userId);
    Optional<StudyRecord> findByIdAndUserId(Long id, Long userId);
    boolean existsByCategoryId(Long categoryId);
}