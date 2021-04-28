package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;

import java.time.LocalDate;

public interface ILessonRepository {
    void create(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;
}