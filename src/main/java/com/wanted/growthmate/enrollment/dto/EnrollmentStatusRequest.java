package com.wanted.growthmate.enrollment.dto;

import com.wanted.growthmate.enrollment.entity.Status;
import jakarta.validation.constraints.NotNull;

public class EnrollmentStatusRequest {

    @NotNull(message = "userId가 비어있습니다.")
    private Long userId;
    @NotNull(message = "강좌 id가 비어있습니다.")
    private Long courseId;
    @NotNull(message = "변경할 상태 값이 비어있습니다.")
    private Status status;

    public EnrollmentStatusRequest(Long userId, Long courseId, Status status) {
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Status getStatus() {
        return status;
    }

}
