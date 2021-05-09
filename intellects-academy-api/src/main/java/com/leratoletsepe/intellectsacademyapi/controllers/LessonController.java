package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/course/{courseId}/lesson")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @PostMapping("")
    public ResponseEntity<Map<String, Boolean>> addLesson(HttpServletRequest request,
                                                     @PathVariable("courseId") Integer courseId,
                                                     @RequestBody Map<String, Object> lessonMap){
        String title = (String) lessonMap.get("title");
        LocalDate lessonDate = LocalDate.parse((String) lessonMap.get("date"));
        String content = (String) lessonMap.get("content");
        lessonService.addLesson(courseId, title, lessonDate, content);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}