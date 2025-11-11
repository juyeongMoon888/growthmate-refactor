package com.wanted.growthmate.learning.course.domain;

import lombok.Builder;

@Builder
public class CourseEdit {
    private String title; //변경이 없다면 기존과 같은값
    private String description;
    private int pointAmount;
    private String imageUrl;

    public void setTitle(String title) {
        this.title = title;
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

    public String getTitle() {
        return title;
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
