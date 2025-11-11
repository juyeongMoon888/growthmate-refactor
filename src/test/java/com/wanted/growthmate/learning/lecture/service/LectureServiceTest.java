package com.wanted.growthmate.learning.lecture.service;

import com.wanted.growthmate.learning.lecture.domain.dto.LectureCreateRequest;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureResponse;
import com.wanted.growthmate.learning.lecture.domain.dto.LectureSummaryResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LectureServiceTest {

    @Autowired
    LectureServiceImpl lectureService;

    @Test
    void save() {
//        Lecture result = lectureService.save();
//        Assertions.assertTrue(result.isVisible());
    }

    @Test
    void findBySectionId() {
        LectureResponse result = lectureService.save(LectureCreateRequest.builder().sectionId(1L).title("제목1").mediaId(1l).courseId(1l).order(1).duration(1l).build());

        Assertions.assertTrue(result.isVisible());

        List<LectureSummaryResponse> result1 = lectureService.findBySectionId(1L);
        Assertions.assertTrue(result1.size() == 0, "section 번허로 된게 없음");

    }
}