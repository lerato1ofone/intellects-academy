package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Note;

import java.time.LocalDate;
import java.util.List;

public interface INoteRepository {
    Integer create(Integer userId, Integer courseId, Integer lessonId, String title, LocalDate date, String content) throws IaBadRequestException;

    Note findById(Integer noteId) throws IaNotFoundException;

    List<Note> findAllForUser(Integer userId) throws IaNotFoundException;

    void removeById(Integer userId, Integer noteId) throws IaNotFoundException;
}