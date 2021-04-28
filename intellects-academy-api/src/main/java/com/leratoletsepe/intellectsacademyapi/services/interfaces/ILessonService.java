package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;

import java.sql.Date;
import java.time.LocalDate;

public interface ILessonService {
    void AddLesson(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;
}