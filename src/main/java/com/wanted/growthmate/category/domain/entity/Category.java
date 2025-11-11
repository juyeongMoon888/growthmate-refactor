package com.wanted.growthmate.category.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    private Long id;
    private String categoryName;
    private String categoryDescription;
    private int categoryOrder;

    public Long getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getCategoryOrder() {
        return categoryOrder;
    }
}
