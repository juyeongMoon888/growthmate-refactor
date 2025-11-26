package com.wanted.growthmate.learning.course.domain.dto;

import com.wanted.growthmate.learning.course.domain.entity.Course;
import lombok.Getter;

@Getter
public class CourseDetailResponse {
    private String title;
    private String description;
    private Long instructorId;
    private Long pointAmount;
    private String imageUrl;
    private Long id;
    private Long categoryId;

    public CourseDetailResponse(Course course) {
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.instructorId = course.getInstructorId();
        this.pointAmount = course.getPointAmount();
        this.imageUrl = course.getImageUrl();
        this.id = course.getId();
        this.categoryId = course.getCategoryId();
    }

    public static CourseDetailResponse from(Course course) {
        return new CourseDetailResponse(course);
    }
}
