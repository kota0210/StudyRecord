package com.example.demo.studyrecord.controller;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.category.security.LoginUserDetails;
import com.example.demo.category.service.CategoryService;
import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.service.StudyRecordService;

@Controller
@RequestMapping("/study-records")
public class StudyRecordController {
    private final StudyRecordService studyRecordService;
    private final CategoryService categoryService;

    public StudyRecordController(StudyRecordService studyRecordService, CategoryService categoryService) {
        this.studyRecordService = studyRecordService;
        this.categoryService = categoryService;
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
     public String showForm(Model model, @AuthenticationPrincipal LoginUserDetails loginUser) {
        Long userId = loginUser.getUser().getId();
         model.addAttribute("studyRecord", new StudyRecord());
         model.addAttribute("categories", categoryService.findAllByUserId(userId));

         return "StudyRecordRegister";
     }

    // 登録処理
    @PostMapping
    public String create(@ModelAttribute("studyRecord") StudyRecord studyRecord, @RequestParam Long categoryId, @AuthenticationPrincipal LoginUserDetails loginUser) {
        studyRecordService.save(studyRecord, loginUser.getUser(), categoryId);
    return "redirect:/study-records";
    }

    // 詳細取得

    // 編集フォームの表示
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model){
        Long loginUserId = 1l;

        StudyRecord studyRecord = studyRecordService.findByIdAndUserId(loginUserId, id);

        model.addAttribute("studyRecord", studyRecord);
        
        return "StudyRecordEdit";
    }

    /// 更新
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute StudyRecord studyRecord) {
        Long loginUserId = 1L;
        studyRecordService.update(id, studyRecord,  loginUserId);

        return "redirect:/study-records";
     }

    // // 削除
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id ,Model model) {
         studyRecordService.delete(id, model);
         return "redirect:/study-records";
     }
}
