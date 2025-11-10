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

    //TODO DTO로
    public CourseDetailResponse createCourse(int tutorId, int courseCategoryId, String courseTitle, String courseDescription, String courseImageUrl, int coursePointAmount) {

        //TODO builder or 생성자로 넣기
        Course newCourse = new Course();

        newCourse.setUserId(tutorId);
        newCourse.setCategoryId(courseCategoryId);
        newCourse.setTitle(courseTitle);
        newCourse.setDescription(courseDescription);
        newCourse.setPointAmount(coursePointAmount);
        newCourse.setCourseState(CourseState.DRAFT);
        newCourse.setImageUrl(courseImageUrl);

        // TODO 엔터티를 response 타입으로 치환 해주는 로직 캡슐화에대해 고민해볼 ㄱ서
        courseRepository.save(newCourse);
        return new CourseDetailResponse(newCourse.getTitle(), newCourse.getDescription(), newCourse.getUserId(), newCourse.getPointAmount(), newCourse.getImageUrl());
    }

    public Optional<Course> getCourse(int courseId) {
        return courseRepository.findById(courseId);
    }

    //TODO:  바로 return, .map으로 변환하기
    public List<CourseDetailResponse> getCourses() {
        // TODO 소스 리팩터링
        List<CourseDetailResponse> res = new ArrayList<>();
        courseRepository.findAll().stream().forEach(course -> res.add(
                // TODO 엔터티를 response 타입으로 치환 해주는 로직 캡슐화에대해 고민해볼 ㄱ서
                new CourseDetailResponse(
                        course.getTitle(),
                        course.getDescription(),
                        course.getUserId(),
                        course.getPointAmount(),
                        course.getImageUrl())
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

        return new CourseDetailResponse(course.getTitle(), course.getDescription(), course.getUserId(), course.getPointAmount(), course.getImageUrl());
    }

    public void deleteCourse(int course_id) {
        Optional<Course> findCourse = courseRepository.findById(course_id);
        if (findCourse.isPresent()) {
            courseRepository.deleteById(course_id);
        }
    }
}
