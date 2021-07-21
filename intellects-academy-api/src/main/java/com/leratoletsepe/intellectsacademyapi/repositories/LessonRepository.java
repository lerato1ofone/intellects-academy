package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.ILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;

@Repository
public class LessonRepository implements ILessonRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_LESSONS(LESSON_ID, TITLE, LESSON_DATE, CONTENT, COURSE_ID) " +
            "VALUES(NEXTVAL('IA_LESSONS_SEQ'), ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setDate(2,  Date.valueOf(date));
                ps.setString(3, content);
                ps.setInt(4, courseId);
                return ps;
            }, keyHolder);
        } catch (Exception e) {
            throw new IaBadRequestException("Failed to create lesson");
        }
    }
}