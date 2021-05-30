package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Note;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.INoteRepository;
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
public class NoteRepository implements INoteRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_NOTES(NOTE_ID, TITLE, NOTE_DATE, CONTENT, USER_ID, LESSON_ID) " +
            "VALUES(NEXTVAL('IA_NOTES_SEQ'), ?, ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Integer userId, Integer courseId, Integer lessonId, String title, LocalDate date, String content) throws IaBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, title);
                ps.setDate(2,  Date.valueOf(date));
                ps.setString(3, content);
                ps.setInt(4, userId);
                ps.setInt(5, lessonId);
                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("USER_ID");

        } catch (Exception e) {
            throw new IaBadRequestException("Failed to create note, try again later");
        }
    }
}