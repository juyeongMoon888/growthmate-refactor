package com.wanted.growthmate.learning.course.controller;

import com.wanted.growthmate.category.dto.CategoryResponse;
import com.wanted.growthmate.learning.course.domain.dto.CourseDetailResponse;
import com.wanted.growthmate.learning.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        List<CourseDetailResponse> courses = courseService.getCourses();
        List<CategoryResponse> categories = courseService.getAllCategories();
        model.addAttribute("courses", courses);
        model.addAttribute("categories", categories);
        return "course-list";
    }
    //@GetMapping("/createCourse")

}
