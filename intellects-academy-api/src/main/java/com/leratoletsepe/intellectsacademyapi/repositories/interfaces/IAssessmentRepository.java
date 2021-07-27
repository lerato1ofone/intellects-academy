package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;

import java.time.LocalDate;

public interface IAssessmentRepository {
    Integer create(String title, LocalDate date, Integer courseId) throws IaBadRequestException;
}