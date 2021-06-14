package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Note;

import java.time.LocalDate;
import java.util.List;

public interface INoteService {
    Note addNote(Integer userId, Integer lessonId, Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException;

    Note getNote(Integer noteId) throws IaNotFoundException;

    List<Note> getNotes(Integer user) throws IaNotFoundException;

    void deleteNote(Integer userId, Integer noteId) throws IaNotFoundException;
}