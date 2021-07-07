package com.leratoletsepe.intellectsacademyapi.models;

public class Course {

    private Integer courseId;
    private Integer userId;
    private String title;
    private String description;
    private Lesson[] lessons;

    public Course(Integer courseId, Integer userId, String title, String description, Lesson[] lessons) {
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

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }
}