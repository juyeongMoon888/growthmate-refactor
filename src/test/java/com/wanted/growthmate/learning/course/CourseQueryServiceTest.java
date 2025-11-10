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

    @Test
    void 강좌_단일_조회() throws Exception {
        CourseCreateRequest dto = new CourseCreateRequest();
        dto.setUserId(1); // 강사ID
        dto.setCategoryId(1);
        dto.setTitle("강좌 제목");
        dto.setDescription("강좌 설명");
        dto.setImageUrl("이미지url");
        dto.setPointAmount(200); // 200P

        courseQueryService.createCourse(dto.getUserId(), dto.getCategoryId(), dto.getTitle(), dto.getDescription(), dto.getImageUrl(), dto.getPointAmount());

        Optional<Course> findCourse = courseQueryService.getCourse(1);
        assertThat(findCourse).isPresent();
        Course course = findCourse.get();
        assertThat(course.getTitle()).isEqualTo("강좌 제목");
        assertThat(course.getDescription()).isEqualTo("강좌 설명");
    }

    @Test
    void 강좌_리스트_조회() throws Exception {
        CourseCreateRequest dto1 = new CourseCreateRequest();
        dto1.setUserId(1); // 강사ID
        dto1.setCategoryId(1);
        dto1.setTitle("강좌 제목");
        dto1.setDescription("강좌 설명");
        dto1.setImageUrl("이미지url");
        dto1.setPointAmount(200); // 200P

        courseQueryService.createCourse(dto1.getUserId(), dto1.getCategoryId(), dto1.getTitle(), dto1.getDescription(), dto1.getImageUrl(), dto1.getPointAmount());

        CourseCreateRequest dto2 = new CourseCreateRequest();
        dto2.setUserId(1); // 강사ID
        dto2.setCategoryId(1);
        dto2.setTitle("강좌 제목2");
        dto2.setDescription("강좌 설명2");
        dto2.setImageUrl("이미지url2");
        dto2.setPointAmount(500); // 200P

        courseQueryService.createCourse(dto2.getUserId(), dto2.getCategoryId(), dto2.getTitle(), dto2.getDescription(), dto2.getImageUrl(), dto2.getPointAmount());

        List<CourseDetailResponse> savedCourses = courseQueryService.getCourses();
        assertThat(savedCourses).isNotEmpty();
        assertThat(savedCourses).hasSize(2); //요소 길이
    }

    @Test
    void 강좌_수정() throws Exception {
        CourseCreateRequest dto3 = new CourseCreateRequest();
        dto3.setUserId(1); // 강사ID
        dto3.setCategoryId(1);
        dto3.setTitle("강좌 제목");
        dto3.setDescription("강좌 설명");
        dto3.setImageUrl("이미지url");
        dto3.setPointAmount(200); // 200P

        courseQueryService.createCourse(dto3.getUserId(), dto3.getCategoryId(), dto3.getTitle(), dto3.getDescription(), dto3.getImageUrl(), dto3.getPointAmount());

        CourseEdit courseEdit = new CourseEdit();
        courseEdit.setUserId(1);
        courseEdit.setCategoryId(1);
        courseEdit.setTitle("강좌 수정");
        courseEdit.setDescription("강좌 설명");
        courseEdit.setImageUrl("이미지url");
        courseEdit.setPointAmount(200);

        CourseDetailResponse updatedCourse = courseQueryService.update(1, courseEdit);
        org.junit.jupiter.api.Assertions.assertEquals(updatedCourse.getTitle(), "강좌 수정");
    }

    @Test
    void 강좌_삭제() throws Exception {
        CourseCreateRequest dto5 = new CourseCreateRequest();
        dto5.setUserId(1); // 강사ID
        dto5.setCategoryId(1);
        dto5.setTitle("강좌 제목");
        dto5.setDescription("강좌 설명");
        dto5.setImageUrl("이미지url");
        dto5.setPointAmount(200); // 200P

        courseQueryService.createCourse(dto5.getUserId(), dto5.getCategoryId(), dto5.getTitle(), dto5.getDescription(), dto5.getImageUrl(), dto5.getPointAmount());

        courseQueryService.deleteCourse(1);
        assertThat(courseQueryService.getCourse(1)).isEmpty();
    }
}