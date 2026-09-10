package com.example.demo.category.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.category.entity.Category;
import com.example.demo.category.security.LoginUserDetails;
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
        return "CategoryList";
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

    // 登録フォーム表示
     @GetMapping("/new")
     public String showForm(Model model) {
         model.addAttribute("category", new Category());
         return "CategoryRegister";
     }

    //  編集フォーム表示
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isEmpty()) {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "指定されたカテゴリが見つかりませんでした");
	}
	    model.addAttribute("category", categoryOpt.get());

	    return "CategoryEdit";
    }


    // 更新
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @AuthenticationPrincipal LoginUserDetails loginUser, Model model,RedirectAttributes redirectAttributes){


    try {
        categoryService.update(id, loginUser.getUser().getId(), name);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを更新しました。");
        return "redirect:/categories";

    } catch (IllegalArgumentException e) {
        System.out.println("カテゴリ更新エラー: " + e.getMessage());

        Category category = categoryService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "指定されたカテゴリが見つかりませんでした"));

        category.setName(name);

        model.addAttribute("category", category);
        model.addAttribute("errorMessage", e.getMessage());

        return "CategoryEdit";
    }
    }

    // 削除
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal LoginUserDetails loginUser, RedirectAttributes redirectAttributes){
        System.out.println("===== カテゴリ削除POSTに到達 =====");
         System.out.println("id = " + id);

        try{
            categoryService.delete(id, loginUser.getUser().getId());
            redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");
        }catch(IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/categories";
    }
}
