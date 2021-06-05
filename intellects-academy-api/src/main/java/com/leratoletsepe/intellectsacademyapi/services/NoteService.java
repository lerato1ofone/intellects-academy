package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.repositories.NoteRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoteService implements INoteService {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public Note addNote(Integer userId, Integer lessonId, Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException {
        Integer noteId = noteRepository.create(userId, lessonId, courseId, title, date, content);
        if(noteId == null)
            throw new IaBadRequestException("Failed to create note");

        Note note = noteRepository.findById(noteId);
        return note;
    }

    @Override
    public Note getNote(Integer noteId) throws IaNotFoundException {
        Note note = noteRepository.findById(noteId);
        if(note == null)
            throw new IaNotFoundException("Note not found");

        return note;
    }

    @Override
    public List<Note> getNotes(Integer user) throws IaNotFoundException {
        List<Note> notes = noteRepository.findAllForUser(user);
        if(notes == null)
            throw new IaNotFoundException("Notes not found for user");

        return notes;
    }
}