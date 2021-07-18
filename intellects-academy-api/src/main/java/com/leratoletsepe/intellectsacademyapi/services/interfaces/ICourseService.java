package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Course;

public interface ICourseService {
    void addCourse(Integer userId, Course course) throws IaBadRequestException;
}