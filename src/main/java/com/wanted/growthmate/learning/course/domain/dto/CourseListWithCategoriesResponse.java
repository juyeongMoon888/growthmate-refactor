package com.wanted.growthmate.learning.course.domain.dto;

import com.wanted.growthmate.category.dto.CategoryResponse;

import java.util.List;

public class CourseListWithCategoriesResponse {
    List<CourseDetailResponse> courses;
    List<CategoryResponse> categories;

    public CourseListWithCategoriesResponse(List<CourseDetailResponse> courses, List<CategoryResponse> categories) {
        this.courses = courses;
        this.categories = categories;
    }

    public static CourseListWithCategoriesResponse of (List<CourseDetailResponse> courses, List<CategoryResponse> categories) {
        return new CourseListWithCategoriesResponse(courses, categories);
    }
}
