package com.wanted.growthmate.enrollment.dto;

import jakarta.validation.constraints.NotNull;

public class EnrollmentChangeOrderRequest {

    @NotNull(message = "강의 id가 비어있습니다.")
    private Long enrollmentId;
    @NotNull(message = "변경할 orderNum이 비어있습니다.")
    private Long newOrderNum;

    public EnrollmentChangeOrderRequest(Long enrollmentId, Long newOrderNum) {
        this.enrollmentId = enrollmentId;
        this.newOrderNum = newOrderNum;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Long getNewOrderNum() {
        return newOrderNum;
    }

    public void setNewOrderNum(Long newOrderNum) {
        this.newOrderNum = newOrderNum;
    }
}
