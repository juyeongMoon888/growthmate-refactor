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
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPointAmount(int pointAmount) {
        this.pointAmount = pointAmount;
    }

    public void setCourseState(CourseState courseState) {
        this.courseState = courseState;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
