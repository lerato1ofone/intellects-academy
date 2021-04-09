package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;

import java.time.LocalDate;

public interface ILessonService {
    void addLesson(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;

    void deleteLesson(Integer userId, Integer lessonId) throws IaNotFoundException;
}