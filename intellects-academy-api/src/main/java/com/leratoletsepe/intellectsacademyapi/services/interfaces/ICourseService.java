package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;

import java.util.List;

public interface ICourseService {
    void addCourse(Integer userId, String title, String description, List<Lesson> lessons) throws IaBadRequestException;
}