package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Status;
import jakarta.validation.constraints.NotNull;

public class EnrollmentStatusRequest {

    @NotNull(message = "userId가 비어있습니다.")
    private Long enrollmentId;
    @NotNull(message = "변경할 상태 값이 비어있습니다.")
    private Status status;

    public EnrollmentStatusRequest(Long enrollmentId, Status status) {
        this.enrollmentId = enrollmentId;
        this.status = status;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public Status getStatus() {
        return status;
    }
}
