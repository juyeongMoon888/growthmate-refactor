package com.wanted.growthmate.learning.course.domain.dto;

import com.wanted.growthmate.learning.course.domain.entity.Course;
import lombok.Getter;

@Getter
public class TutorCourseSummaryResponse {
    private Long id;
    private String title;
    private String description;
    private String status;

    private String imageUrl; //확장성
    private Long categoryId; //확장성

    public TutorCourseSummaryResponse(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.status = course.getCourseState().name();
    }

    public static TutorCourseSummaryResponse from(Course course) {
        return new TutorCourseSummaryResponse(course);
    }
}
