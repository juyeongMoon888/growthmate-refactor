package com.wanted.growthmate.learning.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseQueryServiceTest {

    @Autowired
    CourseQueryService courseQueryService;

    @Test
    void 강좌_생성() throws Exception{
        CourseCreateRequest dto = new CourseCreateRequest();
        dto.setUserId(1); // 강사ID
        dto.setCategoryId(1);
        dto.setTitle("강좌 제목");
        dto.setDescription("강좌 설명");
        dto.setImageUrl("이미지url");
        dto.setPointAmount(200); // 200P

        CourseDetailResponse savedCourse = courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());
        assertThat(savedCourse.getTitle()).isEqualTo("강좌 제목");

    }
}