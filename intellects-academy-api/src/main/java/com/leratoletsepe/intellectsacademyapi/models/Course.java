package com.leratoletsepe.intellectsacademyapi.models;

import java.util.List;

public class Course {

    private Integer courseId;
    private Integer userId;
    private String title;
    private String description;
    private List<Lesson> lessons;

    public Course(Integer courseId, Integer userId, String title, String description, List<Lesson> lessons) {
        this.courseId = courseId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.lessons = lessons;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}