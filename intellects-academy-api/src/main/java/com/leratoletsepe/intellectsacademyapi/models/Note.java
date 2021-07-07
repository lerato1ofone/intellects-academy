package com.leratoletsepe.intellectsacademyapi.models;

import java.sql.Date;

public class Note {

    private Integer noteId;
    private String title;
    private Date noteDate;
    public String content;
    private Integer userId;
    private Integer lessonId;

    public Note(Integer noteId, String title, Date noteDate, String content, Integer userId, Integer lessonId) {
        this.noteId = noteId;
        this.title = title;
        this.noteDate = noteDate;
        this.content = content;
        this.userId = userId;
        this.lessonId = lessonId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }
}