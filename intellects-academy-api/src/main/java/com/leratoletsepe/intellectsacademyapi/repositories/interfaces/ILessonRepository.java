package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface ILessonRepository {
    Integer create(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;

    List<Lesson> findAll(Integer courseId) throws IaNotFoundException;

    void removeById(Integer userId, Integer lessonId) throws IaNotFoundException;

    Lesson findById(Integer lessonId) throws IaNotFoundException;

    Lesson update(Integer lessonId, String title, LocalDate date, String content) throws IaBadRequestException;
}