package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.repositories.CourseRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService implements ICourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addCourse(Integer userId, Course course) throws IaBadRequestException {
        courseRepository.create(userId, course.getCourseId(), course.getTitle(), course.getDescription(), course.getLessons());
    }
}