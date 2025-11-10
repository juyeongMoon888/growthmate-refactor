package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Enrollment;

public class EnrollmentResponse {

    private Long id;
    private Long userId;
    private Long courseId;
    private String status;
    private String progress;

    public EnrollmentResponse() {}

    public EnrollmentResponse(Enrollment e) {
        this.id = e.getId();
        this.userId = e.getUserId();
        this.courseId = e.getCourseId();
        this.status = e.getStatus().name();
        this.progress = e.getProgress().name();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }

    public String getProgress() {
        return progress;
    }
}
