package com.example.demo.category.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.category.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 一覧の表示
    // LoginUserDetailsは、Spring Securityのユーザーデータを格納するクラスで、@AuthenticationPrincipalアノテーションを使用して、現在認証されているユーザーの情報を取得するために使用されます。
    @GetMapping()
    public String index(Model model, @AuthenticationPrincipal LoginUserDetails loginUser ){
        Long userId = loginUser.getUser().getId();

        model.addAttribute("categories", categoryService.findAllByUserId(userId));
        return "categories/index";
    }

    // 登録
    @PostMapping
    public String create(@RequestParam String name, @AuthenticationPrincipal LoginUserDetails loginUser, RedirectAttributes redirectAttributes){
        try{
            categoryService.create(loginUser.getUser(), name);
            redirectAttributes.addFlashAttribute("successMessage","カテゴリを登録しました。");
        } catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/categories";
    }

    // 更新
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @RequestParam String name, @AuthenticationPrincipal LoginUserDetails loginUser, RedirectAttributes redirectAttributes){
        try{
            categoryService.update(id, loginUser.getUser().getId(), name);
            redirectAttributes.addFlashAttribute("successMessage", "カテゴリを更新しました。");
        }catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/categories";
    }

    // 削除
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal LoginUserDetails loginUser, RedirectAttributes redirectAttributes){
        try{
            categoryService.delete(id, loginUser.getUser().getId());
            redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");
        }catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/categories";
    }
}
