package com.wanted.growthmate.learning.course.domain.dto;

import com.wanted.growthmate.learning.course.domain.entity.Course;

public class TutorCourseSummaryResponse {
    private int id;
    private String title;
    private String description;
    private String status;

    private String imageUrl; //확장성
    private int categoryId; //확장성

    public TutorCourseSummaryResponse(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.status = course.getCourseState().name();
    }

    public static TutorCourseSummaryResponse from(Course course) {
        return new TutorCourseSummaryResponse(course);
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
