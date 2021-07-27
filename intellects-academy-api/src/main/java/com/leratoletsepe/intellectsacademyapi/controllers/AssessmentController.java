package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.Assessment;
import com.leratoletsepe.intellectsacademyapi.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/assessment")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    @PostMapping("")
    public ResponseEntity<Assessment> addAssessment(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> assessmentMap){
        String title = (String) assessmentMap.get("title");
        LocalDate date = LocalDate.parse((String) assessmentMap.get("date"));
        Integer courseId = Integer.parseInt((String) assessmentMap.get("courseId"));

        Assessment assessment = assessmentService.addAssessment(title, date, courseId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }
}