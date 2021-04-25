package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.ILessonService;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class LessonService implements ILessonService {

    @Override
    public void AddLesson(Integer courseId, String title, Date date, String content) throws IaBadRequestException {

    }
}
