package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.Assessment;
import com.leratoletsepe.intellectsacademyapi.services.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
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
        Integer courseId = (Integer) assessmentMap.get("courseId");

        Assessment assessment = assessmentService.addAssessment(title, date, courseId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @GetMapping("/{assessmentId}")
    public ResponseEntity<Assessment> getAssessment(HttpServletRequest request,
                                                    @PathVariable("assessmentId") Integer assessmentId){
        Assessment assessment = assessmentService.getAssessment(assessmentId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @PutMapping("/{assessmentId}")
    public ResponseEntity<Assessment> updateAssessment(HttpServletRequest request,
                                                    @PathVariable("assessmentId") Integer assessmentId,
                                                    @RequestBody Map<String, Object> assessmentMap) {
        String title = (String) assessmentMap.get("title");
        LocalDate date = LocalDate.parse((String) assessmentMap.get("date"));
        Integer courseId = (Integer) assessmentMap.get("courseId");

        Assessment assessment = assessmentService.updateAssessment(assessmentId, title, date, courseId);
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    @DeleteMapping("/{assessmentId}")
    public ResponseEntity<Map<String, Boolean>> deleteAssessment(HttpServletRequest request,
                                                             @PathVariable("assessmentId") Integer assessmentId){
        assessmentService.deleteAssessment(assessmentId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}