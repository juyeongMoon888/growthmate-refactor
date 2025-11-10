package com.wanted.growthmate.enrollment.dto;

import jakarta.validation.constraints.NotNull;


public class EnrollmentSearchRequest {

    @NotNull(message = "userId가 비어있습니다.")
    private Long userId;

    public EnrollmentSearchRequest() {
    }

    public EnrollmentSearchRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
