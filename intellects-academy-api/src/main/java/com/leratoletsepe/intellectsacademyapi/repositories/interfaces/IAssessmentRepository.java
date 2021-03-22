package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Assessment;

import java.time.LocalDate;

public interface IAssessmentRepository {
    Integer create(String title, LocalDate date, Integer courseId) throws IaBadRequestException;

    Assessment findById(Integer assessmentId) throws IaNotFoundException;

    Assessment update(Integer assessmentId, String title, LocalDate date,  Integer courseId) throws IaBadRequestException;

    void removeById(Integer assessmentId) throws IaBadRequestException;
}