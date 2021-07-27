package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Assessment;
import com.leratoletsepe.intellectsacademyapi.repositories.AssessmentRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.IAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AssessmentService implements IAssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Override
    public Assessment addAssessment(String title, LocalDate date, Integer courseId) throws IaBadRequestException {
        Integer assessmentId = assessmentRepository.create(title, date, courseId);
        if(assessmentId == null)
            throw new IaBadRequestException("Failed to create assessment");

        Assessment assessment = this.getAssessment(assessmentId);
        return assessment;
    }

    @Override
    public Assessment getAssessment(Integer assessmentId) throws IaNotFoundException {
        Assessment assessment = assessmentRepository.findById(assessmentId);
        if(assessment == null)
            throw new IaBadRequestException("Failed to get assessment");

        return assessment;
    }
}