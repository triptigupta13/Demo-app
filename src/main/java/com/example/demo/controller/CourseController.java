package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Courses;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @GetMapping("/course")
    public List<Courses> getAllCourses(){
        return this.courseService.findAll();
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable(value = "id") long courseId)
        throws ResourceNotFoundException {
        Courses courses = courseService.findById(courseId);
        return ResponseEntity.ok().body(courses);
    }

    @PostMapping("/course")
    public Courses addCourse(@RequestBody Courses courses){
        return this.courseRepository.save(courses);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Courses> updateCourse(@PathVariable(value = "id") long courseId ,
                                                @Validated @RequestBody Courses courseDetails) throws ResourceNotFoundException{

        Courses courses = courseService.findById(courseId);
        courses.setCourseName(courseDetails.getCourseName());
        courses.setCourseCode(courseDetails.getCourseCode());
        courses.setDepartment(courseDetails.getDepartment());
        courses.setFacultyName(courseDetails.getFacultyName());

        return ResponseEntity.ok(this.courseRepository.save(courses));

    }

    @DeleteMapping("/course/{id}")
    public Map<String,Boolean> deleteCourse(@PathVariable(value = "id") long courseId)throws ResourceNotFoundException {
        Courses courses = courseService.findById(courseId);
        this.courseRepository.delete(courses);
        Map<String,Boolean> response= new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    
}
