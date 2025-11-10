package com.wanted.growthmate.learning.course.dto;

public class CourseDetailResponse {
    private String title;
    private String description;
    private int userId; // 강사ID
    private int pointAmount;
    private String imageUrl;

    public CourseDetailResponse(String title, String description, int userId, int pointAmount, String imageUrl) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.pointAmount = pointAmount;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
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
