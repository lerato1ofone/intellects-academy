package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.IAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class AssessmentRepository implements IAssessmentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String title, LocalDate date, Integer courseId) throws IaBadRequestException {
       try {

       } catch (Exception e) {
           throw new IaBadRequestException("Failed to create assessment, try again later.");
       }
    }
}