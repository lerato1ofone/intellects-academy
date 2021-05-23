package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.repositories.NoteRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NoteService implements INoteService {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public Note addNote(Integer userId, Integer lessonId, Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException {
        Note note = noteRepository.create(userId, lessonId, courseId, title, date, content);

        if(note == null)
            throw new IaBadRequestException("Failed to create note");
        return note;
    }
}