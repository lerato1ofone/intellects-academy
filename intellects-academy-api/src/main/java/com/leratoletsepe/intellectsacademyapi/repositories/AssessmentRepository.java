package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Assessment;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.IAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;

@Repository
public class AssessmentRepository implements IAssessmentRepository {

    private static final String SQL_CREATE = "INSERT INTO IA_ASSESSMENTS(ASSESSMENT_ID, TITLE, ASSESSMENT_DATE, COURSE_ID) " +
            "VALUES(NEXTVAL('IA_ASSESSMENTS_SEQ'), ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT ASSESSMENT_ID, TITLE, ASSESSMENT_DATE, COURSE_ID " +
            "FROM IA_ASSESSMENTS WHERE ASSESSMENT_ID = ?";

    private static final String SQL_UPDATE = "UPDATE IA_ASSESSMENTS SET TITLE = ?, ASSESSMENT_DATE = ?, COURSE_ID = ? " +
            "WHERE ASSESSMENT_ID = ?";

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

    @Override
    public Assessment findById(Integer assessmentId) throws IaNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{ assessmentId }, assessmentRowMapper);
        } catch (Exception e) {
            throw new IaNotFoundException("Assessment not found, try again later");
        }
    }

    @Override
    public Assessment update(Integer assessmentId, String title, LocalDate date, Integer courseId) throws IaBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{title, date, courseId, assessmentId});
            return this.findById(assessmentId);
        } catch (Exception e) {
            throw new IaBadRequestException("Invalid request details. Failed to update assessment");
        }
    }

    @Override
    public void removeById(Integer assessmentId) throws IaBadRequestException {

    }

    private RowMapper<Assessment> assessmentRowMapper = ((rs, rowNumber) -> {
        return new Assessment(
                rs.getInt("ASSESSMENT_ID"),
                rs.getString("TITLE"),
                LocalDate.parse(rs.getString("ASSESSMENT_DATE")),
                rs.getInt("COURSE_ID"));
    });
}