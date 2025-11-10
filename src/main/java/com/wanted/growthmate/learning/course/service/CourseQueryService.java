package com.wanted.growthmate.learning.course.service;

import com.wanted.growthmate.learning.course.repository.CourseRepository;
import com.wanted.growthmate.learning.course.domain.CourseState;
import com.wanted.growthmate.learning.course.domain.Course;
import com.wanted.growthmate.learning.course.dto.CourseDetailResponse;
import com.wanted.growthmate.learning.course.dto.CourseEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseQueryService {

    //TODO 리팩터링하기 @Autowired가 있는데 왜 또 생성자가?
    @Autowired
    private CourseRepository courseRepository;

    public CourseQueryService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDetailResponse createCourse(int tutorId, int courseCategoryId, String courseTitle, String courseDescription, String courseImageUrl, int coursePointAmount) {
        Course newCourse = Course.builder()
                .userId(tutorId)
                .categoryId(courseCategoryId)
                .title(courseTitle)
                .description(courseDescription)
                .pointAmount(coursePointAmount)
                .courseState(CourseState.DRAFT)
                .imageUrl(courseImageUrl)
                .build();

        courseRepository.save(newCourse);
        return CourseDetailResponse.from(newCourse);
    }

    public Optional<Course> getCourse(int courseId) {
        return courseRepository.findById(courseId);
    }

    //TODO:  바로 return, .map으로 변환하기
    public List<CourseDetailResponse> getCourses() {
        // TODO 소스 리팩터링
        List<CourseDetailResponse> res = new ArrayList<>();
        List<Course> course = courseRepository.findAll();
        courseRepository.findAll().stream().forEach(course -> res.add(
                // TODO 엔터티를 response 타입으로 치환 해주는 로직 캡슐화에대해 고민해볼 ㄱ서
                CourseDetailResponse.from(course)
        ));
        return res;
    }

    public CourseDetailResponse update(int course_id, CourseEdit courseEdit) throws IllegalAccessException {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new IllegalAccessException("Course not found with id: " + course_id));

        course.setTitle(courseEdit.getTitle());
        course.setDescription(courseEdit.getDescription());
        course.setCategoryId(courseEdit.getCategoryId());
        course.setUserId(courseEdit.getUserId());
        course.setPointAmount(courseEdit.getPointAmount());
        course.setImageUrl(courseEdit.getImageUrl());

        courseRepository.save(course);

        return CourseDetailResponse.from(course);
    }

    public void deleteCourse(int course_id) {
        Optional<Course> findCourse = courseRepository.findById(course_id);
        if (findCourse.isPresent()) {
            courseRepository.deleteById(course_id);
        }
    }
}
