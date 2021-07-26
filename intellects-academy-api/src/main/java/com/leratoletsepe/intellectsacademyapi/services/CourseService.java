package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.repositories.CourseRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addCourse(Integer userId, String title, String description, List<Lesson> lessons) throws IaBadRequestException {
        courseRepository.create(userId, title, description, lessons);
    }

    @Override
    public Course getCourse(Integer courseId) throws IaNotFoundException {
        return courseRepository.findById(courseId);
    }

    @Override
    public Course updateCourse(Integer userId, Integer courseId, String title, String description, List<Lesson> lessons) throws IaBadRequestException {
        courseRepository.update(userId, courseId, title, description, lessons);
        Course course = courseRepository.findById(courseId);
        if(course == null)
            throw new IaBadRequestException("Failed to updated course");

        return course;
    }
}