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

    @Column(nullable = false)
    private String title;

    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Column(nullable = false)
    private String description;

    @Column(name = "user_id", nullable = false)
    private int userId; // 강사ID

    @Column(name = "point_amount", nullable = false)
    private int pointAmount;

    @Column(name = "course_state", nullable = false)
    private CourseState courseState;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
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
        if (courseEdit.getTitle() != null) {
            this.title = courseEdit.getTitle();
        }
        if (courseEdit.getDescription() != null) {
            this.description = courseEdit.getDescription();
        }
        if (courseEdit.getImageUrl() != null) {
            this.imageUrl = courseEdit.getImageUrl();
        }
        this.pointAmount = courseEdit.getPointAmount();
    }
}
