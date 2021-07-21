package com.leratoletsepe.intellectsacademyapi.controllers;

import com.leratoletsepe.intellectsacademyapi.models.Course;
import com.leratoletsepe.intellectsacademyapi.models.Lesson;
import com.leratoletsepe.intellectsacademyapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("")
    public ResponseEntity<Map<String, Boolean>> addCourse(HttpServletRequest request,
                                                    @RequestBody Map<String, Object> courseMap){
        Integer userId = (Integer) request.getAttribute("userId");
        String title = (String) courseMap.get("title");
        String descriptions = (String) courseMap.get("description");
        List<Lesson> lessonList = (List<Lesson>) courseMap.get("lessons");
        courseService.addCourse(userId, title, descriptions, lessonList);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(HttpServletRequest request,
                                            @PathVariable("courseId") Integer courseId){
        Course course = courseService.getCourse(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}