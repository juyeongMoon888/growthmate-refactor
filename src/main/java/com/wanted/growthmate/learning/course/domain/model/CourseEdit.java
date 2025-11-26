package com.wanted.growthmate.learning.course.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseEdit {
    private String title;
    private String description;
    private Long pointAmount;
    private String imageUrl;
}
