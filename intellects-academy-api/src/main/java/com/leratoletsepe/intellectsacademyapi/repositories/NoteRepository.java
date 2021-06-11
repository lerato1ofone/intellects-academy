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
import java.util.List;

@Repository
public class NoteRepository implements INoteRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_NOTES(NOTE_ID, TITLE, NOTE_DATE, CONTENT, USER_ID, LESSON_ID) " +
            "VALUES(NEXTVAL('IA_NOTES_SEQ'), ?, ?, ?, ?, ?)";

    private static  final String SQL_FIND_BY_ID = "SELECT NOTE_ID, TITLE, NOTE_DATE, CONTENT, USER_ID, LESSON_ID " +
            "FROM IA_NOTES WHERE NOTE_ID = ?";

    private static final String SQL_FIND_BY_USER = "SELECT NOTE_ID, TITLE, NOTE_DATE, CONTENT, USER_ID, LESSON_ID " +
            "FROM IA_NOTES WHERE USER_ID = ?";

    private static final String SQL_DELETE_NOTE = "DELETE FROM IA_NOTES WHERE USER_ID = ? AND NOTE_ID = ?";

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

            return (Integer) keyHolder.getKeys().get("NOTE_ID");
        } catch (Exception e) {
            throw new IaBadRequestException("Failed to create note, try again later.");
        }
    }

    @Override
    public Note findById(Integer noteId) throws IaNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{ noteId }, noteRowMapper);
        } catch (Exception e) {
            throw new IaNotFoundException("Note not found, try again later.");
        }
    }

    @Override
    public List<Note> findAllForUser(Integer userId) throws IaNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_USER, new Object[]{ userId }, noteRowMapper);
        } catch (Exception e) {
            throw new IaNotFoundException("Notes not found for user, try again later.");
        }
    }

    @Override
    public void removeById(Integer userId, Integer noteId) throws IaNotFoundException {
        try {
            jdbcTemplate.update(SQL_DELETE_NOTE, new Object[]{userId, noteId});

        } catch (Exception e){
            throw new IaNotFoundException("Note not found, try again later.");
        }
    }

    private RowMapper<Note> noteRowMapper = ((rs, rowNumber) -> {
        return new Note(
                rs.getInt("NOTE_ID"),
                rs.getString("TITLE"),
                rs.getDate("NOTE_DATE"),
                rs.getString("CONTENT"),
                rs.getInt("USER_ID"),
                rs.getInt("LESSON_ID"));
    });
}