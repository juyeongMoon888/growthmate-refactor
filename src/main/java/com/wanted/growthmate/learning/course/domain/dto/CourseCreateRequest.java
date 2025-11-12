package com.wanted.growthmate.learning.course.domain.dto;

import lombok.Builder;

@Builder
public class CourseCreateRequest {
    private String title;
    private int categoryId;
    private String description;
    private int userId; // 강사ID
    private int pointAmount;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
