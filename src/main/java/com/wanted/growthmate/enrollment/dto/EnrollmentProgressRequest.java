package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Progress;
import jakarta.validation.constraints.NotNull;

public class EnrollmentProgressRequest {

    @NotNull(message = "userId 값이 비어있습니다.")
    private Long userId;
    @NotNull(message = "courseId 값이 비어있습니다.")
    private Long courseId;
    @NotNull(message = "진행률 값이 비어있습니다.")
    private Progress progress;

    public EnrollmentProgressRequest(Long userId, Long courseId, Progress progress) {
        this.userId = userId;
        this.courseId = courseId;
        this.progress = progress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
