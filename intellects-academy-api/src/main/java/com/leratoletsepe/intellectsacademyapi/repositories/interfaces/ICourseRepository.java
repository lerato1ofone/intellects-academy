package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;

import java.util.List;

public interface ICourseRepository {
    void create(Integer userId, Integer courseId, String title, String description, List<Lesson> lessons) throws IaBadRequestException;
}