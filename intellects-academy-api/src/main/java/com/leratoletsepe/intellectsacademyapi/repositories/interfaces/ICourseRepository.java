package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;

import java.util.List;

public interface ICourseRepository {
    void create(Integer userId, String title, String description, List<Lesson> lessons) throws IaBadRequestException;

    Course findById(Integer courseId) throws IaNotFoundException;

    void addLesson(Integer courseId, List<Lesson> lessons) throws IaBadRequestException;
}