package com.wanted.growthmate.learning.course.domain.entity;

import com.wanted.growthmate.learning.course.domain.model.CourseEdit;
import com.wanted.growthmate.learning.course.domain.model.CourseState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor //new Course가 완전히 없어지면 없애도됨
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int categoryId;
    private String description;
    private int userId; // 강사ID
    private int pointAmount;
    private CourseState courseState;

    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Course() {}

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public int getUserId() {
        return userId;
    }

    public int getPointAmount() {
        return pointAmount;
    }

    public CourseState getCourseState() {
        return courseState;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void editCourse(CourseEdit courseEdit) {
        this.title = courseEdit.getTitle();
        this.description = courseEdit.getDescription();
        this.pointAmount = courseEdit.getPointAmount();
        this.imageUrl = courseEdit.getImageUrl();
    }
}
