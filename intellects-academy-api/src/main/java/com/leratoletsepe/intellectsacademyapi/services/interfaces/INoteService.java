package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Note;

import java.time.LocalDate;

public interface INoteService {
    Note addNote(Integer userId, Integer lessonId, Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;
}