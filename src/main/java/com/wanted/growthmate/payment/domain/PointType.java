package com.wanted.growthmate.payment.domain;

// NOTE: 도메인 로직에서 헷갈릴 수 있어서 양수(EARN), 음수(USE)를 Enum에서 구분하였습니다.
public enum PointType {
    // NOTE: 1차 구현 범위 ----------
    // 수강신청 시 학생 포인트 차감 (-)
    ENROLLMENT_STUDENT_USE,
    // 수강신청 시 강사 포인트 적립 (+)
    ENROLLMENT_INSTRUCTOR_EARN,
    // 수강취소 시 학생 포인트 환불 (+)
    WITHDRAWAL_STUDENT_EARN,
    // 수강취소 시 강사 포인트 회수 (-)
    WITHDRAWAL_INSTRUCTOR_USE,
    // 포인트 충전 (+)
    PAYMENT_EARN,
    // ---------------------------
    // 이벤트 보상 (+)
    EVENT_EARN,
    // 챌린지 등 보상 (+)
    CHALLENGE_EARN,
    // 관리자 수동 차감 (-)
    ADMIN_ADJUST_USE,
    // 관리자 수동 적립 (+)
    ADMIN_ADJUST_EARN;
}
