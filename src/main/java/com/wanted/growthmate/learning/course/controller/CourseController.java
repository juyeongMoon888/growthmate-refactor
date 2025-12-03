package com.wanted.growthmate.learning.course.controller;

import com.wanted.growthmate.category.dto.CategoryResponse;
import com.wanted.growthmate.learning.course.domain.dto.*;
import com.wanted.growthmate.learning.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /** 수강생이 보는 강좌 목록 **/
    @GetMapping("/courses")
    public String showCourseListPage(Model model) {
        CourseListWithCategoriesResponse courseListView = courseService.getCourseListView();
        model.addAttribute("courseListView", courseListView);
        return "course-list";
    }

    /** 강좌 목록 **/
    @GetMapping("/instructor/courses")
    public String showInstructorCoursesPage(Model model) {
        List<InstructorCourseSummaryResponse> instructorCourses = courseService.getInstructorDraftCourses();
        model.addAttribute("courses", instructorCourses);
        return "instructor-course-list";
    }

    /** 새 강좌 등록 폼 요청 **/
    @GetMapping("/instructor/courses/new")
    public String newInstructorCourse(Model model) {
        model.addAttribute("form", new CourseCreateRequest());

        List<CategoryResponse> categories = courseService.getAllCategories();
        model.addAttribute("categories", categories);
        return "course/course-new";
    }

    /** 폼 제출 -> 생성 **/
    @PostMapping("/instructor/courses")
    public String createInstructorCourse(@ModelAttribute("form") CourseCreateRequest request) {
        courseService.createCourse(
                1L,
                request
        );
        return "redirect:/instructor/courses";
    }

    /**
     * 강좌 수정 폼 화면을 보여준다.
     * 주어진 강좌 ID로 수정용 데이터를 조회해 모델에 담고,
     * 강좌 수정 템플릿을 반환한다.
     */
    @GetMapping("/instructor/courses/{id}/edit")
    public String editCourseForm(@PathVariable("id") Long courseId, Model model) {
        CourseEditRequest courseEditForm = courseService.getInstructorDraftCourseEditForm(courseId);
        model.addAttribute("form", courseEditForm);
        return "course/course-new";
    }
}
