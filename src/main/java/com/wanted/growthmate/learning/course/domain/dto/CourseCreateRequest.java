package com.wanted.growthmate.learning.course.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CourseCreateRequest {
    private String title;
    private Long categoryId;
    private String description;
    private Long instructorId;
    private Long pointAmount;
    private String imageUrl;

    public CourseCreateRequest() {}

}
