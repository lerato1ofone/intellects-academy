package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;

import java.time.LocalDate;

public interface ILessonService {
    void addLesson(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;
}