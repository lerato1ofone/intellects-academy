package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.ILessonRepository;
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
public class LessonRepository implements ILessonRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_LESSONS(LESSON_ID, TITLE, LESSON_DATE, CONTENT, COURSE_ID) " +
            "VALUES(NEXTVAL('IA_LESSONS_SEQ'), ?, ?, ?, ?)";

    private static final String SQL_FIND_BY_COURSE_ID = "SELECT LESSON_ID, TITLE, LESSON_DATE, CONTENT, COURSE_ID " +
            "FROM IA_LESSONS WHERE COURSE_ID = ?";

    private static final String SQL_FIND_ID = "SELECT LESSON_ID, TITLE, LESSON_DATE, CONTENT, COURSE_ID " +
            "FROM IA_LESSONS WHERE LESSON_ID = ?";

    private static final String SQL_DELETE_LESSON = "DELETE FROM IA_LESSONS WHERE USER_ID = ? AND LESSON_ID = ?";

    private static final String SQL_UPDATE = "UPDATE IA_LESSONS SET TITLE = ?, LESSON_DATE = ?, CONTENT = ? " +
            "WHERE LESSON_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException {
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

            return (Integer) keyHolder.getKeys().get("LESSON_ID");
        } catch (Exception e) {
            throw new IaBadRequestException("Failed to create lesson");
        }
    }

    @Override
    public List<Lesson> findAll(Integer courseId) throws IaNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_COURSE_ID, new Object[]{ courseId }, lessonRowMapper);
        } catch (Exception e) {
            throw new IaNotFoundException("Lessons not found, try again later.");
        }
    }

    @Override
    public void removeById(Integer userId, Integer lessonId) throws IaNotFoundException {
        try {
            jdbcTemplate.update(SQL_DELETE_LESSON, new Object[]{userId, lessonId});
        } catch (Exception e){
            throw new IaNotFoundException("Lesson not found, try again later.");
        }
    }

    @Override
    public Lesson findById(Integer lessonId) throws IaNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[]{ lessonId }, lessonRowMapper);
        } catch (Exception e){
            throw new IaNotFoundException("Course not found");
        }
    }

    @Override
    public Lesson update(Integer lessonId, String title, LocalDate date, String content) throws IaBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{title, date, content, lessonId});
            return this.findById(lessonId);
        } catch (Exception e) {
            throw new IaBadRequestException("Invalid request details. Failed to update lesson");
        }
    }

    private RowMapper<Lesson> lessonRowMapper = ((rs, rowNumber) -> {
        return new Lesson(
                rs.getInt("LESSON_ID"),
                rs.getString("TITLE"),
                LocalDate.parse(rs.getString("LESSON_DATE")),
                rs.getString("CONTENT"),
                rs.getInt("COURSE_ID"));
    });
}