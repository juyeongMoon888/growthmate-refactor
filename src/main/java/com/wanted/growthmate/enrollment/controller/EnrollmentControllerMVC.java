package com.wanted.growthmate.enrollment.controller;

import com.wanted.growthmate.enrollment.dto.EnrollmentChangeOrderRequest;
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

    private EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentControllerMVC(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/myc")
    public String myCourses(Model model) {
        Long userId = 1L;
        Status status = Status.ACTIVE;
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByUserId(
                new EnrollmentSearchRequest(userId, status));

        model.addAttribute("userId", userId);
        model.addAttribute("enrollments",enrollments);
        return "my";
    }

    @GetMapping("/edit")
    public String editEnrollment(Model model) {
        Long userId = 1L;
        Status status = Status.ACTIVE;
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByUserId(
                new EnrollmentSearchRequest(userId,status));
        model.addAttribute("enrollments",enrollments);
        return "editt";
    }

    @PostMapping("/change-order")
    public String changeOrder(@RequestParam Long enrollmentId, @RequestParam Long newOrderNum) {
        enrollmentService.changeOrder(new EnrollmentChangeOrderRequest(enrollmentId, newOrderNum));
        return "redirect:/edit";
    }

    @PostMapping("/hide")
    public String hideEnrollment(@RequestParam Long enrollmentId) {
        enrollmentService.updateEnrollmentStatus(new EnrollmentStatusRequest(enrollmentId, Status.HIDDEN));
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
        enrollmentService.updateEnrollmentStatus(new EnrollmentStatusRequest(enrollmentId, Status.ACTIVE));
        return "redirect:/edit";
    }

    @PostMapping("/refund")
    public String refundEnrollment(@RequestParam Long enrollmentId) {
        enrollmentService.updateEnrollmentStatus(new EnrollmentStatusRequest(enrollmentId, Status.REFUNDED));
        return "redirect:/edit";
    }
}
