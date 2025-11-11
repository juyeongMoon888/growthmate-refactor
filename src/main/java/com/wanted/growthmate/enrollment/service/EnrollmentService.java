package com.wanted.growthmate.enrollment.service;

import com.wanted.growthmate.enrollment.dto.*;
import com.wanted.growthmate.enrollment.entity.Enrollment;
import com.wanted.growthmate.enrollment.entity.Progress;
import com.wanted.growthmate.enrollment.entity.Status;
import com.wanted.growthmate.enrollment.repository.EnrollmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

//     수강 정보 전체 조회 (사용 계획 X)
//    public List<Enrollment> findAllEnrollment() {
//        return enrollmentRepository.findAll();
//    }

    // 수강 목록 생성 (강좌 조회 및 결제와 동시에 수강 목록 생성)
    public Enrollment createEnrollment(@Valid EnrollmentRequest request) {
        Enrollment lastEnrollment = enrollmentRepository.findTopByUserIdOrderByOrderNumDesc(request.getUserId());
        int newOrderNum = (lastEnrollment != null) ? lastEnrollment.getOrderNum() + 1 : 1;

        Enrollment enrollment = new Enrollment(request.getUserId(), request.getCourseId());
        enrollment.setOrderNum(newOrderNum);

        return enrollmentRepository.save(enrollment);
    }

    // userId로 수강중인 목록 조회
    public List<Enrollment> findEnrollmentByUserId(@Valid EnrollmentSearchRequest request) {
        return enrollmentRepository.findByUserIdAndStatus(request.getUserId(),request.getStatus());
    }

    // userId, courseId로 수강 중인 강좌 단일 조회
    public EnrollmentResponse findEnrollmentByUserIdAndCourseId(EnrollmentRequest request) {
        return enrollmentRepository.findByUserIdAndCourseId(
                request.getUserId(), request.getCourseId())
                .map(EnrollmentResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 수강 정보가 존재하지 않습니다."));
    }

    // 강좌의 상태를 변경 (ACTIVE, HIDDEN, REFUNDED)
    @Transactional
    public EnrollmentResponse updateEnrollmentStatus(@Valid EnrollmentStatusRequest request) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(
                request.getUserId(), request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("수강 정보가 없습니다."));

        // status를 Refunded로 변경할 때, progress 확인 진행률이 true인 경우 변경 불가
        if (request.getStatus() == Status.REFUNDED) {
            if (enrollment.getProgress() != Progress.FALSE) {
                throw new IllegalStateException("수강 진행 중에는 환불이 불가합니다.");
            }
            enrollment.setDeletedAt(LocalDateTime.now());
        }

        enrollment.setStatus(request.getStatus());

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(updatedEnrollment);
    }

    @Transactional
    public EnrollmentResponse updateEnrollmentProgress(EnrollmentProgressRequest request) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(
                // 강좌 확인
                request.getUserId(), request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("수강 정보가 없습니다."));

        // 진행률 수정
        enrollment.setProgress(request.getProgress());

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(updatedEnrollment);
    }


    public EnrollmentResponse deleteEnrollment(EnrollmentStatusRequest request) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(
                // 강좌 확인
                request.getUserId(), request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("수강 정보가 없습니다."));

        // 강좌 진행률 확인
        if (enrollment.getProgress() != Progress.FALSE) {
            throw new IllegalStateException("수강을 시작한 강좌는 환불이 불가 합니다.");
        }
        // 상태와 삭제 일시 삽입 후 저장
        enrollment.setDeletedAt(LocalDateTime.now());
        enrollmentRepository.save(enrollment);
        return new EnrollmentResponse(enrollment);
    }

}
