package com.wanted.growthmate.payment.service;

import com.wanted.growthmate.payment.domain.Point;
import com.wanted.growthmate.payment.repository.PointRepository;
import com.wanted.growthmate.user.entity.User;
import com.wanted.growthmate.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// TODO: 예외 처리 & 트랜잭션
@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

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
