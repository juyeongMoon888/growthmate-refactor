package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.repository.PointRepository;
import com.wanted.growthmate.payment.repository.PointTransactionRepository;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;
    private final UserRepository userRepository;

    public PointServiceImpl(
            PointRepository pointRepository,
            PointTransactionRepository pointTransactionRepository,
            UserRepository userRepository
    ) {
        this.pointRepository = pointRepository;
        this.pointTransactionRepository = pointTransactionRepository;
        this.userRepository = userRepository;
    }

    // TODO: 로그인한 사용자의 포인트 전체 내역 조회
    //List<PointTransaction> getPointTransactions(Long userId);

    // TODO: 결제하여 포인트 충전 시 포인트 적립
    //PointTransaction chargePoints(Long userId, int amount, PaymentMethod paymentMethod, PaymentType paymentType);

    /*
     * 로그인한 사용자의 현재 포인트 조회
     */
    @Transactional
    public Point getOrCreatePoint(Long userId) {
        // 1. 기존 포인트 계정 조회
        Optional<Point> optionalPoint = pointRepository.findByUserId(userId);

        // 2. 존재하면 바로 반환
        if (optionalPoint.isPresent()) {
            return optionalPoint.get();
        }

        // 3. 존재하지 않으면 User를 조회
        // TODO: Custom Exception
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. userId=" + userId));

        // 4. 새 Point 생성
        // TODO: 도메인 메서드로 감싸기
        Point newPoint = new Point(user, 0);

        // 5. 저장 후 반환
        return pointRepository.save(newPoint);
    }

    // TODO: 사용자가 수강신청 시 포인트 차감
    //PointTransaction deductPointForEnrollment(Long userId, Long enrollmentId, int amount);

    // TODO: 사용자가 수강신청 시 포인트 적립 (강사 포인트)
    //PointTransaction addPointForEnrollmentInstructor(Long instructorId, Long enrollmentId, int amount);
}
