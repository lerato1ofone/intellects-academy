package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
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

    @Override
    public void deleteLesson(Integer userId, Integer lessonId) throws IaNotFoundException {
        lessonRepository.removeById(userId, lessonId);
    }

    @Override
    public List<Lesson> getLessons(Integer courseId) throws IaNotFoundException {
        List<Lesson> lessons = lessonRepository.findAll(courseId);
        if(lessons == null)
            throw new IaNotFoundException("No lessons found");

        return lessons;
    }

    @Override
    public Lesson getLesson(Integer lessonId) throws IaNotFoundException {
        Lesson lesson = lessonRepository.findById(lessonId);
        if(lesson == null)
            throw new IaNotFoundException("Lesson not found");

        return lesson;
    }

    @Override
    public Lesson updateLesson(Integer lessonId, String title, LocalDate date, String content) throws IaBadRequestException {
        Lesson lesson = lessonRepository.update(lessonId, title, date, content);
        if(lesson == null)
            throw new IaBadRequestException("Failed to update lesson");

        return lesson;
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