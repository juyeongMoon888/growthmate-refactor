package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;

public interface PointService {

    // NOTE: 유저 도메인에서 사용해주세요 -------------------
    // 로그인한 사용자의 현재 포인트 조회 (원장이 없으면 생성)
    Point getOrCreatePoint(Long userId);
    // ---------------------------------------------

    // NOTE: 수강 도메인에서 사용해주세요 -------------------
    // 사용자가 수강신청 시 포인트 차감
    //PointTransaction deductPointForEnrollment(Long userId, Long enrollmentId, int amount);

    // 사용자가 수강신청 시 포인트 적립 (강사 포인트)
    //PointTransaction addPointForEnrollmentInstructor(Long instructorId, Long enrollmentId, int amount);
    // ----------------------------------------------
}
