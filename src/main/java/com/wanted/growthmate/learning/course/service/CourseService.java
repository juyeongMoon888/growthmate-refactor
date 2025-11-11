package com.wanted.growthmate.learning.course.service;

import com.wanted.growthmate.learning.course.exception.CourseNotFound;
import com.wanted.growthmate.learning.course.repository.CourseRepository;
import com.wanted.growthmate.learning.course.domain.CourseState;
import com.wanted.growthmate.learning.course.domain.Course;
import com.wanted.growthmate.learning.course.dto.CourseDetailResponse;
import com.wanted.growthmate.learning.course.domain.CourseEdit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<CourseDetailResponse> getCourses() {
        return courseRepository.findAll().stream()
                .map(CourseDetailResponse::from)
                .toList();
    }

    public CourseDetailResponse update(int course_id, CourseEdit courseEdit) {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new CourseNotFound("Course not found with id: " + course_id));

        course.editCourse(courseEdit);

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
