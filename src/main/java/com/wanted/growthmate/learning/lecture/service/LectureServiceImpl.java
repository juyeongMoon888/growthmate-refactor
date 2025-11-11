package com.wanted.growthmate.learning.lecture.service;

import com.wanted.growthmate.learning.lecture.domain.dto.LectureCreateRequest;
import com.wanted.growthmate.learning.lecture.domain.entity.Lecture;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureResponse;
import com.wanted.growthmate.learning.lecture.exception.LectureNotFoundException;
import com.wanted.growthmate.learning.lecture.repository.LectureRepository;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {
    @Autowired
    LectureRepository lectureRepository;

    @Override
    public LectureResponse save(LectureCreateRequest lectureCreateRequest) {
        return LectureResponse.from(
                lectureRepository.save(lectureCreateRequest.toEntity())
        );
    }

    @Override
    public List<LectureSummaryResponse> findByCourseId(Long courseId) {
        return lectureRepository.findByCourseId(courseId).stream().map(lecture ->
            LectureSummaryResponse.from(lecture)
        ).collect(Collectors.toList());
    }

    @Override
    public List<LectureSummaryResponse> findBySectionId(Long sectionId) {
        return lectureRepository.findBySectionIdOrderByDisplayOrderAsc(sectionId).stream().map(lecture ->
                LectureSummaryResponse.from(lecture)
        ).collect(Collectors.toList());
    }

    @Override
    public LectureResponse findByLectureId(Long lectureId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new LectureNotFoundException(lectureId));
        return LectureResponse.from(lecture);
    }

}
