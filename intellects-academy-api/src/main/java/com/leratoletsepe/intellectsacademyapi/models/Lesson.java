package com.leratoletsepe.intellectsacademyapi.models;

import java.sql.Date;

public class Lesson {

    private Integer lessonId;
    private String title;
    private Date lessonDate;
    private String content;
    private Integer courseId;

    public Lesson(Integer lessonId, String title, Date lessonDate, String content, Integer courseId) {
        this.lessonId = lessonId;
        this.title = title;
        this.lessonDate = lessonDate;
        this.content = content;
        this.courseId = courseId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}