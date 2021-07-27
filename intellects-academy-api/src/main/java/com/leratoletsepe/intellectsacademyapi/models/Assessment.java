package com.leratoletsepe.intellectsacademyapi.models;

import java.time.LocalDate;

public class Assessment {
    private Integer assessmentId;
    private String title;
    private LocalDate assessmentDate;
    private Integer courseId;

    public Assessment(Integer assessmentId, String title, LocalDate assessmentDate, Integer courseId) {
        this.assessmentId = assessmentId;
        this.title = title;
        this.assessmentDate = assessmentDate;
        this.courseId = courseId;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}