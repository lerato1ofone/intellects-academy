package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import java.sql.Date;

public interface ILessonRepository {
    void create(Integer courseId, String title, Date date, String content);
}
