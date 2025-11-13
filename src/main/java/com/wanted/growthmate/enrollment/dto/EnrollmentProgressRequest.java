package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Progress;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EnrollmentProgressRequest {

    @NotNull(message = "enrollmentId 값이 비어있습니다.")
    @Positive(message = "Id는 0보다 커야 합니다.")
    private Long enrollmentId;
    @NotNull(message = "진행률 값이 비어있습니다.")
    private Progress progress;

    public EnrollmentProgressRequest(Long enrollmentId, Progress progress) {
        this.enrollmentId = enrollmentId;
        this.progress = progress;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
