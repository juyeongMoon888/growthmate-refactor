package com.wanted.growthmate.learning.course.dto;

public class CourseCreateRequest {
    private String title;
    private int categoryId;
    private String description;
    private int userId; // 강사ID
    private int pointAmount;
    private String imageUrl;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
