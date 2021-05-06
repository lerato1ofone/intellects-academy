package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.repositories.CourseRepository;
import com.leratoletsepe.intellectsacademyapi.repositories.LessonRepository;
import com.leratoletsepe.intellectsacademyapi.services.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService implements ILessonService {

    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addLesson(Integer courseId, String title, LocalDate date, String content) throws IaBadRequestException {
        Integer lessonId = lessonRepository.create(courseId, title, date, content);
        Course course = courseRepository.findById(courseId);
        List<Lesson> lessons = addLessonToLessonsList(course.getLessons(), lessonId, title, date, content, courseId);

        courseRepository.addLesson(courseId, lessons);
    }

    private List<Lesson> addLessonToLessonsList(List<Lesson> lessonList, Integer lessonId, String title, LocalDate date, String content, Integer courseId){
        Lesson lesson = new Lesson(
                lessonId,
                title,
                date,
                content,
                courseId
        );
        ArrayList<Lesson> les = new ArrayList<>(lessonList);
        les.add(lesson);
        return les;
    }
}