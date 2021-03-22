package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Assessment;

import java.time.LocalDate;

public interface IAssessmentService {
    Assessment addAssessment(String title, LocalDate date, Integer courseId) throws IaBadRequestException;

    Assessment getAssessment(Integer assessmentId) throws IaNotFoundException;

    Assessment updateAssessment(Integer assessmentId, String title, LocalDate date, Integer courseId) throws IaBadRequestException;

    void deleteAssessment(Integer assessmentId) throws IaBadRequestException;
}