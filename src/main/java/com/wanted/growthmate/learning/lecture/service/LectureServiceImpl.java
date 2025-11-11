package com.wanted.growthmate.learning.lecture.service;

import com.wanted.growthmate.learning.lecture.domain.dto.LectureCreateRequest;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureResponse;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureSummaryResponse;

import java.util.List;

public interface LectureServiceImpl {
    LectureResponse save(LectureCreateRequest lectureCreateRequest) ;

    List<LectureSummaryResponse> findByCourseId(Long courseId);

    List<LectureSummaryResponse> findBySectionId(Long sectionId) ;

    LectureResponse findByLectureId(Long lectureId);

}
