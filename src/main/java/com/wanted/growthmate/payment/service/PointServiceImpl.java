package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.domain.PointTransaction;
import com.wanted.growthmate.payment.domain.enums.PaymentMethod;
import com.wanted.growthmate.payment.domain.enums.PaymentType;
import com.wanted.growthmate.payment.repository.PointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    // 로그인한 사용자의 포인트 전체 내역 조회
    //List<PointTransaction> getPointTransactions(Long userId);

    // 결제하여 포인트 충전 시 포인트 적립
    //PointTransaction chargePoints(Long userId, int amount, PaymentMethod paymentMethod, PaymentType paymentType);

    // 로그인한 사용자의 현재 포인트 조회
    public Point getCurrentPoint(Long userId) {
        return pointRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 포인트 원장이 존재하지 않습니다."));
    }

    // 사용자가 수강신청 시 포인트 차감
    //PointTransaction deductPointForEnrollment(Long userId, Long enrollmentId, int amount);

    // 사용자가 수강신청 시 포인트 적립 (강사 포인트)
    //PointTransaction addPointForEnrollmentInstructor(Long instructorId, Long enrollmentId, int amount);
}
