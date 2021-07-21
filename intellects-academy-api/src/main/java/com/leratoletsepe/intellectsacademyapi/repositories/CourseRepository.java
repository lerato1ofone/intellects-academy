package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class CourseRepository implements ICourseRepository {

    private static  final String SQL_CREATE = "INSERT INTO IA_COURSES(COURSE_ID, USER_ID, TITLE, DESCRIPTION, LESSONS) " +
            "VALUES(NEXTVAL('IA_COURSES_SEQ'), ?, ?, ?, ?)";

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
}