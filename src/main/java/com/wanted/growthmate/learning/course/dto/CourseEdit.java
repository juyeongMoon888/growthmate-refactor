package com.wanted.growthmate.learning.course.dto;

import lombok.Builder;

@Builder
public class CourseEdit {
    private int userId;
    private String title; //변경이 없다면 기존과 같은값
    private int categoryId;
    private String description;
    private int pointAmount;
    private String imageUrl;

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
