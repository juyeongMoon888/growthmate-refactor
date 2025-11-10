package com.wanted.growthmate.learning.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseQueryService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseQueryService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDetailResponse createCourse(int tutorId, int courseCategoryId, String courseTitle, String courseDescription, String courseImageUrl, int coursePointAmount) {

        Course newCourse = new Course();

        newCourse.setUserId(tutorId);
        newCourse.setCategoryId(courseCategoryId);
        newCourse.setTitle(courseTitle);
        newCourse.setDescription(courseDescription);
        newCourse.setPointAmount(coursePointAmount);
        newCourse.setCourseState(CourseState.DRAFT);
        newCourse.setImageUrl(courseImageUrl);

        courseRepository.save(newCourse);
        return new CourseDetailResponse(newCourse.getTitle(), newCourse.getDescription(), newCourse.getUserId(), newCourse.getPointAmount(), newCourse.getImageUrl());
    }
}
