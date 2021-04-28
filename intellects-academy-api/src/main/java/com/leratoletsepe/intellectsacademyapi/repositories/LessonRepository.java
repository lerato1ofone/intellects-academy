package com.leratoletsepe.intellectsacademyapi.repositories;

import com.leratoletsepe.intellectsacademyapi.repositories.interfaces.ILessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class LessonRepository implements ILessonRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Integer courseId, String title, Date date, String content) {

    }
}