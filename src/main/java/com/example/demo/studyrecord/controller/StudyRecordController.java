package com.example.demo.studyrecord.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.repository.StudyRecordRepository;
import com.example.demo.studyrecord.service.StudyRecordService;

@Controller
@RequestMapping("/StudyRecord")
public class StudyRecordController {
    private final StudyRecordService studyRecordService;

    public StudyRecordController(StudyRecordService studyRecordService) {
        this.studyRecordService = studyRecordService;
    }

    // 一覧表示
    @GetMapping
    public String list(Model model) {
        List<StudyRecord> studyRecords = studyRecordService.findAll();
        model.addAttribute("studyRecords", studyRecords);
        return "StudyRecordList";
    }

    // 登録フォーム表示
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("studyRecord", new StudyRecord());
        return "StudyRecordResister";
    }

    // 登録処理
    @PostMapping
    public String create(@ModelAttribute StudyRecord studyRecord) {
        studyRecordService.save(studyRecord);
        return "redirect:/study-records";
    }

    // 詳細取得

    /// 更新
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
         Optional<StudyRecord> studyRecord;
         try {
            studyRecord = StudyRecordRepository.findById(id);
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            studyRecord = Optional.empty();
         }
         model.addAttribute("studyRecord", studyRecord);
         return "StudyRecordEdit";
     }

    // // 削除
    // @PostMapping("/{id}/delete")
    // public String delete(@PathVariable Long id) {
    //     studyRecordService.deleteById(id);
    //     return "redirect:/study-records";
    // }
}
