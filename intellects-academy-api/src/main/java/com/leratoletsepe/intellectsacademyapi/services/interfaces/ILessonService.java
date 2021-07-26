package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface ILessonService {
    void addLesson(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;

    void deleteLesson(Integer userId, Integer lessonId) throws IaNotFoundException;

    List<Lesson> getLessons(Integer courseId) throws IaNotFoundException;

    Lesson getLesson(Integer lessonId) throws IaNotFoundException;

    Lesson updateLesson(Integer lessonId,String title, LocalDate date, String content) throws IaBadRequestException;
}