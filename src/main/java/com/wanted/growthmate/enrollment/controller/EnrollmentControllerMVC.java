package com.wanted.growthmate.enrollment.controller;

import com.wanted.growthmate.enrollment.dto.EnrollmentSearchRequest;
import com.wanted.growthmate.enrollment.dto.EnrollmentStatusRequest;
import com.wanted.growthmate.enrollment.entity.Enrollment;
import com.wanted.growthmate.enrollment.entity.Status;
import com.wanted.growthmate.enrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EnrollmentControllerMVC {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/myc")
    public String myCourses(Model model) {
        Long userId = 1L;
        Status status = Status.ACTIVE;
        EnrollmentSearchRequest enrollmentSearchRequest = new EnrollmentSearchRequest(userId,status);
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByUserId(enrollmentSearchRequest);

        model.addAttribute("userId", userId);
        model.addAttribute("enrollments",enrollments);
        return "my";
    }

    @GetMapping("/edit")
    public String editEnrollment(Model model) {

        Long userId = 1L;
        EnrollmentSearchRequest enrollmentSearchRequest = new EnrollmentSearchRequest(userId,Status.ACTIVE);
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByUserId(enrollmentSearchRequest);
        model.addAttribute("enrollments",enrollments);
        return "editt";
    }

    @PostMapping("/change-order")
    public String changeOrder(@RequestParam Long enrollmentId, @RequestParam Long newOrderNum) {
        Long userId = 1L;       // test

        enrollmentService.changeOrder(userId, enrollmentId, newOrderNum);

        return "redirect:/edit";
    }

    @PostMapping("/hide")
    public String hideEnrollment(@RequestParam Long enrollmentId) {
        Long userId = 1L;
        enrollmentService.updateEnrollmentStatus1(userId, enrollmentId, Status.HIDDEN);
        return "redirect:/edit";
    }

    @GetMapping("/hidden")
    public String showHiddenEnrollment(Model model) {
        Long userId = 1L;
        Status status = Status.HIDDEN;
        EnrollmentSearchRequest enrollmentSearchRequest = new EnrollmentSearchRequest(userId,status);
        List<Enrollment> hiddenEnrollments = enrollmentService.findEnrollmentByUserId(enrollmentSearchRequest);
        model.addAttribute("enrollments",hiddenEnrollments);
        model.addAttribute("userId", userId);
        return "hid";
    }

    @PostMapping("/restore")
    public String restoreEnrollment(@RequestParam Long enrollmentId) {
        Long userId = 1L;
        enrollmentService.updateEnrollmentStatus1(userId, enrollmentId, Status.ACTIVE);
        return "redirect:/edit";
    }
}
