package com.example.demo.service;

import com.example.demo.model.Courses;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public void save(Courses courses){
        courseRepository.save(courses);
    }

    public List<Courses> findAll(){
        List<Courses> courses;
        courses = courseRepository.findAll();
        return courses;
    }

    public Courses findById(Long courseId){
        Courses courses = courseRepository.findById(courseId).orElse(null);
        return courses;
    }

    public void deleteById(Long courseId){
        courseRepository.deleteById(courseId);
    }

    public List<Courses> findByStudentId(long studentId){
        List<Courses> courses;
        courses= courseRepository.findByStudentId(studentId);
        return courses;
    }
}
