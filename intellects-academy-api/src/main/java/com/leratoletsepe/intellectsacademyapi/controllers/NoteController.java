package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/{courseId}/{lessonId}")
    public ResponseEntity<Note> addNote(HttpServletRequest request,
                                        @PathVariable("courseId") Integer courseId,
                                        @PathVariable("lessonId") Integer lessonId,
                                        @RequestBody Map<String, Object> noteMap) {
        Integer userId = (Integer) request.getAttribute("userId");
        String title = (String) noteMap.get("title");
        LocalDate lessonDate = LocalDate.parse((String) noteMap.get("date"));
        String content = (String) noteMap.get("content");

        Note note = noteService.addNote(userId, lessonId, courseId, title, lessonDate, content);
        return new ResponseEntity<>(note, HttpStatus.OK);
    }
}