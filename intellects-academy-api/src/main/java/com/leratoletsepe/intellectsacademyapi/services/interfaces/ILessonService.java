package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;

import java.sql.Date;

public interface ILessonService {
    void AddLesson(Integer courseId, String title, Date date, String content) throws IaBadRequestException;
}