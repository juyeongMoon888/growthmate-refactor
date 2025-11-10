package com.wanted.growthmate.enrollment.repository;

import com.wanted.growthmate.enrollment.dto.EnrollmentResponse;
import com.wanted.growthmate.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository("enrollment_repository")
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // 단일 강좌 조회 optional로 null 확인
    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);

    // userId로 수강중인 강좌 리스트 확인
    List<Enrollment> findByUserId(Long userId);
}
