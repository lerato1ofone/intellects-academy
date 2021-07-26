package com.leratoletsepe.intellectsacademyapi.repositories;

import com.google.gson.Gson;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CourseRepository implements ICourseRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_COURSES(COURSE_ID, USER_ID, TITLE, DESCRIPTION, LESSONS) " +
            "VALUES(NEXTVAL('IA_COURSES_SEQ'), ?, ?, ?, ?)";

    private static final String SQL_ADD_LESSON = "UPDATE IA_COURSES SET LESSONS = ? WHERE COURSE_ID = ?";

    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, COURSE_ID, TITLE, DESCRIPTION, LESSONS FROM IA_COURSES WHERE COURSE_ID = ?";

    private static final String SQL_UPDATE = "UPDATE IA_COURSES SET TITLE = ?, DESCRIPTION = ?, LESSONS = ? " +
            "WHERE COURSE_ID = ? AND USER_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Integer userId, String title, String description, List<Lesson> lessons) throws IaBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                ps.setObject(4, lessons, Types.OTHER);
                return ps;
            }, keyHolder);

        } catch (Exception e) {
            throw new IaBadRequestException("Couldn't create the course, try again later.");
        }
    }

    @Override
    public Course findById(Integer courseId) throws IaNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{ courseId }, courseRowMapper);
        } catch (Exception e){
            throw new IaNotFoundException("Course not found");
        }
    }

    @Override
    public void addLesson(Integer courseId, List<Lesson> lessons) throws IaBadRequestException {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_LESSON);
                ps.setObject(1, lessons, Types.OTHER);
                ps.setInt(2, courseId);
                return ps;
            });

        } catch (Exception e) {
            throw new IaBadRequestException("Failed to add lesson, try again later.");
        }
    }

    @Override
    public void update(Integer userId, Integer courseId, String title, String description, List<Lesson> lessons) throws IaBadRequestException {
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
                ps.setString(1, title);
                ps.setString(2, description);
                ps.setObject(3, lessons, Types.OTHER);
                ps.setInt(4, courseId);
                ps.setInt(5, userId);
                return ps;
            });
        } catch (Exception e) {
            throw new IaBadRequestException("Invalid request details. Failed to update lesson");
        }
    }

    private RowMapper<Course> courseRowMapper = ((rs, rowNumber) -> {
        var lessonsResultsSet = rs.getString("LESSONS");
        List<Lesson> lessons = lessonsResultsSet == null ? new ArrayList<>() :
                Arrays.asList(new Gson().fromJson(lessonsResultsSet, Lesson[].class));

        return new Course (
                rs.getInt("USER_ID"),
                rs.getInt("COURSE_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                lessons
        );
    });
}