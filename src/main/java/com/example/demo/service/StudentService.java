package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void save(Student student){
        studentRepository.save(student);
    }

    public List<Student> findAll(){
        List<Student> student;
        student=studentRepository.findAll() ;
        return student;
    }

    public Student findById(Long student_id){
        Student student = studentRepository.findById(student_id).orElse(null);
        return student;
    }

    public void deleteById(Long studentId){
        studentRepository.deleteById(studentId);
    }
}
