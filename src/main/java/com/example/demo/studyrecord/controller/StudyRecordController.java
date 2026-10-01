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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.category.security.LoginUserDetails;
import com.example.demo.category.service.CategoryService;
import com.example.demo.studyrecord.entity.StudyRecord;
import com.example.demo.studyrecord.form.StudyRecordForm;
import com.example.demo.studyrecord.service.StudyRecordService;

import jakarta.validation.Valid;

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
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, @AuthenticationPrincipal LoginUserDetails loginUser, Model model){
        Long userId = loginUser.getUser().getId();

    StudyRecord studyRecord = studyRecordService.findByIdAndUserId(userId, id);

    StudyRecordForm form = new StudyRecordForm();
    form.setTitle(studyRecord.getTitle());
    form.setContent(studyRecord.getContent());
    form.setStudyDate(studyRecord.getStudyDate());
    form.setDurationMinutes(studyRecord.getDurationMinutes());
    form.setCategoryId(studyRecord.getCategory().getId());

    model.addAttribute("studyRecordForm", form);
    model.addAttribute("studyRecordId", id);
    model.addAttribute("categories", categoryService.findAllByUserId(userId));

    return "StudyRecordEdit";
    }

    /// 更新
    @PostMapping("/edit/{id}")
    public String update(
        @PathVariable Long id,
        @Valid @ModelAttribute("studyRecordForm") StudyRecordForm form,
        BindingResult bindingResult,
        @AuthenticationPrincipal LoginUserDetails loginUser,
        Model model,
        RedirectAttributes redirectAttributes
) {
    Long userId = loginUser.getUser().getId();

    if (bindingResult.hasErrors()) {
        model.addAttribute("studyRecordId", id);
        model.addAttribute("categories", categoryService.findAllByUserId(userId));
        return "StudyRecordEdit";
    }

    try {
        studyRecordService.update(id, userId, form);
        redirectAttributes.addFlashAttribute("successMessage", "学習記録を更新しました。");
        return "redirect:/study-records";

    } catch (IllegalArgumentException e) {
        model.addAttribute("studyRecordId", id);
        model.addAttribute("categories", categoryService.findAllByUserId(userId));
        model.addAttribute("errorMessage", e.getMessage());
        return "StudyRecordEdit";
    }
}

    // // 削除
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id ,Model model) {
         studyRecordService.delete(id, model);
         return "redirect:/study-records";
     }
}
