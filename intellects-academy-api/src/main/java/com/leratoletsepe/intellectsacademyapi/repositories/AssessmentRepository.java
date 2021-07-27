package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.IAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

@Repository
public class AssessmentRepository implements IAssessmentRepository {

    private static final String SQL_CREATE = "INTO INTO IA_ASSESSMENTS(ASSESSMENT_ID, TITLE, ASSESSMENT_DATE, COURSE_ID) " +
            "VALUES(NEXTVAL('IA_ASSESSMENTS_SEQ'), ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String title, LocalDate date, Integer courseId) throws IaBadRequestException {
       try {
           KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setDate(2,  Date.valueOf(date));
                ps.setInt(3, courseId);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("ASSESSMENT_ID");
       } catch (Exception e) {
           throw new IaBadRequestException("Failed to create assessment, try again later.");
       }
    }
}