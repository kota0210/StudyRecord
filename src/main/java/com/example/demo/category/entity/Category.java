package com.example.demo.category.entity;

import java.time.LocalDateTime;

import com.example.demo.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories",uniqueConstraints = {@UniqueConstraint(name = "uk_categories_user_name", columnNames = {"user_id", "name"})})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    // カテゴリID
    @Id
    // テーブルのidentity列を利用して、主キーを生成
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // ユーザーID
    // 多対一の関係を定義し、ユーザーエンティティと結合
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public void updateName(String name){
        this.name = name;
    }

    
}
