package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Status;
import jakarta.validation.constraints.NotNull;


public class EnrollmentSearchRequest {

    @NotNull(message = "userId가 비어있습니다.")
    private Long userId;

    @NotNull(message = "상태 값이 비어있습니다.")
    private Status status;

    public EnrollmentSearchRequest(Long userId, Status status) {
        this.userId = userId;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
