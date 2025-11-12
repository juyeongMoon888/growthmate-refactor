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
    @Transactional
    public Enrollment createEnrollment(EnrollmentRequest request) {
        Enrollment lastEnrollment = enrollmentRepository.findTopByUserIdOrderByOrderNumDesc(request.getUserId());
        Long newOrderNum = (lastEnrollment != null) ? lastEnrollment.getOrderNum() + 1 : 1;

        Enrollment enrollment = new Enrollment(request.getUserId(), request.getCourseId());
        enrollment.setOrderNum(newOrderNum);

        return enrollmentRepository.save(enrollment);
    }

    // userId로 수강중인 목록 조회
    @Transactional(readOnly = true)
    public List<Enrollment> findEnrollmentByUserId(EnrollmentSearchRequest request) {
        return enrollmentRepository.findEnrollmentsByUserIdAndStatusOrderByOrderNumAsc(request.getUserId(),request.getStatus());
    }

    // userId, courseId로 수강 중인 강좌 단일 조회 (사용 안함)
    public EnrollmentResponse findEnrollmentByUserIdAndCourseId(EnrollmentRequest request) {
        return enrollmentRepository.findByUserIdAndCourseId(
                request.getUserId(), request.getCourseId())
                .map(EnrollmentResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("해당 수강 정보가 존재하지 않습니다."));
    }

    // 강좌의 상태를 변경 (ACTIVE, HIDDEN, REFUNDED)
    @Transactional
    public EnrollmentResponse updateEnrollmentStatus(EnrollmentStatusRequest request) {
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

        if (request.getStatus() != Status.ACTIVE) {
            List<Enrollment> affectedList = enrollmentRepository.findEnrollmentsByUserIdAndStatusOrderByOrderNumAsc(request.getUserId(), Status.ACTIVE);
            for (Enrollment e : affectedList) {
                if (e.getOrderNum() > enrollment.getOrderNum()) {
                    e.setOrderNum(e.getOrderNum() - 1);
                }
            }
            enrollment.setOrderNum(null);
        } else if (request.getStatus() == Status.ACTIVE){
            Enrollment lastEnrollment = enrollmentRepository.findTopByUserIdOrderByOrderNumDesc(request.getUserId());
            Long newOrderNum = (lastEnrollment != null) ? lastEnrollment.getOrderNum() + 1 : 1;
            enrollment.setStatus(Status.ACTIVE);
            enrollment.setOrderNum(newOrderNum);
        }

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


    @Transactional
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

    @Transactional
    public void changeOrder(Long userId, Long enrollmentId, Long newOrderNum) {
        Enrollment target = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("등록된 강좌가 없다"));
        Long oldOrderNum = target.getOrderNum();

        // ACTIVE 상태의 유저 강좌 조회
        List<Enrollment> enrollments = enrollmentRepository.findEnrollmentsByUserIdAndStatusOrderByOrderNumAsc(userId, Status.ACTIVE);

        // 강좌들의 수
        int maxOrder = enrollments.size();

        for (Enrollment e : enrollments) {
            // 동일 강좌는 나중에 처리
            if (e.getId().equals(target.getId())) {
                continue;
            }

            if (oldOrderNum < newOrderNum) {
                // 뒤로 밀기: oldOrder < newOrder
                // oldOrder+1 ~ newOrder 구간의 강좌는 -1
                if (e.getOrderNum() > oldOrderNum && e.getOrderNum() <= newOrderNum) {
                    e.setOrderNum(e.getOrderNum() - 1);
                }

            } else if (oldOrderNum > newOrderNum) {
                // 앞으로 당기기: oldOrder > newOrder
                // newOrder ~ oldOrder-1 구간의 강좌는 +1
                if (e.getOrderNum() >= newOrderNum && e.getOrderNum() < oldOrderNum) {
                    e.setOrderNum(e.getOrderNum() + 1);
                }
            }
        }
        // 마지막에 대상 강좌 순서 업데이트
        target.setOrderNum(newOrderNum);
    }
}
