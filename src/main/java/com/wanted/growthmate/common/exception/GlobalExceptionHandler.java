package com.wanted.growthmate.common.exception;

import com.wanted.growthmate.learning.lecture.exception.LectureNotFoundException;
import com.wanted.growthmate.learning.section.exception.SectionNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SectionNotFoundException.class)
    public String handleSectionNotFound(SectionNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(LectureNotFoundException.class)
    public String handleLectureNotFound(LectureNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", "오류가 발생했습니다: " + e.getMessage());
        e.printStackTrace();
        return "error/500";
    }
}

