package com.example.demo.category.repository;

import com.example.demo.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser_IdOrderByCreatedAtAsc(Long userId);

    Optional<Category> findByIdAndUser_Id(Long categoryId, Long userId);

    boolean existsByUser_IdAndName(Long userId, String name);
}
