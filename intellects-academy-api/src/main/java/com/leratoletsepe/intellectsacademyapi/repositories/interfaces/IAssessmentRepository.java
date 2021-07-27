package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Assessment;

import java.time.LocalDate;

public interface IAssessmentRepository {
    Integer create(String title, LocalDate date, Integer courseId) throws IaBadRequestException;

    Assessment findById(String assessmentId) throws IaNotFoundException;
}