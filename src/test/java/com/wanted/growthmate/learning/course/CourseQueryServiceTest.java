package com.wanted.growthmate.learning.course;

import com.wanted.growthmate.learning.course.domain.Course;
import com.wanted.growthmate.learning.course.dto.CourseCreateRequest;
import com.wanted.growthmate.learning.course.dto.CourseDetailResponse;
import com.wanted.growthmate.learning.course.dto.CourseEdit;
import com.wanted.growthmate.learning.course.service.CourseQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseQueryServiceTest {

    @Autowired
    CourseQueryService courseQueryService;

    @Test
    void 강좌_생성() throws Exception {
        CourseCreateRequest dto = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();

        CourseDetailResponse savedCourse = courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());
        assertThat(savedCourse.getTitle()).isEqualTo("강좌 제목");
    }

    @Test
    void 강좌_단일_조회() throws Exception {
        CourseCreateRequest dto = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();

        courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());

        Optional<Course> findCourse = courseQueryService.getCourse(1);
        assertThat(findCourse).isPresent();
        Course course = findCourse.get();
        assertThat(course.getTitle()).isEqualTo("강좌 제목");
        assertThat(course.getDescription()).isEqualTo("강좌 설명");
    }

    @Test
    void 강좌_리스트_조회() throws Exception {
        CourseCreateRequest dto1 = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();

        courseQueryService.createCourse(dto1.getUserId(), dto1.getCategoryId(), dto1.getTitle(), dto1.getDescription(), dto1.getImageUrl(), dto1.getPointAmount());

        CourseCreateRequest dto2 = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목2")
                .description("강좌 설명2")
                .imageUrl("이미지url2")
                .pointAmount(200)// 200P
                .build();

        courseQueryService.createCourse(dto2.getUserId(), dto2.getCategoryId(), dto2.getTitle(), dto2.getDescription(), dto2.getImageUrl(), dto2.getPointAmount());

        List<CourseDetailResponse> savedCourses = courseQueryService.getCourses();
        assertThat(savedCourses).isNotEmpty();
        assertThat(savedCourses).hasSize(2); //요소 길이
    }

    @Test
    void 강좌_수정() throws Exception {
        CourseCreateRequest dto = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();

        courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());

        CourseEdit courseEdit = CourseEdit.builder()
                .title("강좌 수정")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();

        CourseDetailResponse updatedCourse = courseQueryService.update(1, courseEdit);
        org.junit.jupiter.api.Assertions.assertEquals(updatedCourse.getTitle(), "강좌 수정");
    }

    @Test
    void 강좌_삭제() throws Exception {
        CourseCreateRequest dto = CourseCreateRequest.builder()
                .userId(1)// 강사ID
                .categoryId(1)
                .title("강좌 제목")
                .description("강좌 설명")
                .imageUrl("이미지url")
                .pointAmount(200)// 200P
                .build();
        courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());

        courseQueryService.deleteCourse(1);
        assertThat(courseQueryService.getCourse(1)).isEmpty();
    }
}