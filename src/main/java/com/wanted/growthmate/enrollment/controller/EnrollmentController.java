package com.wanted.growthmate.enrollment.controller;

import com.wanted.growthmate.enrollment.dto.*;
import com.wanted.growthmate.enrollment.entity.Enrollment;
import com.wanted.growthmate.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // 수강 목록 생성
    @PostMapping
    public Enrollment createEnrollment(@RequestBody @Valid EnrollmentRequest request) {
        return enrollmentService.createEnrollment(request);
    }

    // 유저 id와 강좌 id로 단일 조회 (사용 안함)
    @PostMapping("/detail")
    public EnrollmentResponse findEnrollmentByUserIdAndCourseId(@RequestBody @Valid EnrollmentRequest request) {
        return enrollmentService.findEnrollmentByUserIdAndCourseId(request);
    }

    // 유저 id로 수강중인 강좌 목록 생성
    @PostMapping("/list")
    public List<EnrollmentResponse> findEnrollmentsByUserId(@RequestBody @Valid EnrollmentSearchRequest request) {
        List<Enrollment> enrollments = enrollmentService.findEnrollmentByUserId(request);
        return enrollments.stream()
                .map(EnrollmentResponse::new)
                .collect(Collectors.toList());
    }

    // 강좌의 상태를 변경 (status)
    @PatchMapping("/status")
    public EnrollmentResponse updateEnrollmentStatus(@RequestBody @Valid EnrollmentStatusRequest request) {
        return enrollmentService.updateEnrollmentStatus(request);
    }

    // 강좌의 진도율을 변경 (Progress)
    @PatchMapping("/progress")
    public EnrollmentResponse updateEnrollmentProgress(@RequestBody @Valid EnrollmentProgressRequest request) {
        return enrollmentService.updateEnrollmentProgress(request);
    }

    // userId, courseId로 조회 후
    @DeleteMapping("/delete")
    public EnrollmentResponse deleteEnrollment(@RequestBody @Valid EnrollmentStatusRequest request) {
        enrollmentService.deleteEnrollment(request);
        return enrollmentService.updateEnrollmentStatus(request);
    }
}
