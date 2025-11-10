package com.wanted.growthmate.learning.course.dto;

import com.wanted.growthmate.learning.course.domain.Course;

public class CourseDetailResponse {
    private String title;
    private String description;
    private int userId; // 강사ID
    private int pointAmount;
    private String imageUrl;

    public CourseDetailResponse(Course course) {
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.userId = course.getUserId();
        this.pointAmount = course.getPointAmount();
        this.imageUrl = course.getImageUrl();
    }

    public static CourseDetailResponse from(Course course) {
        return new CourseDetailResponse(course);
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
