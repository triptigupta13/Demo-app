package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId){
        Student student= studentService.findById(studentId);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @RequestBody Student studentDetails){
        Student student = studentService.findById(studentId);
        student.setStudentName(studentDetails.getStudentName());
        student.setEmail(studentDetails.getEmail());
        student.setPassword(studentDetails.getPassword());
        return ResponseEntity.ok(studentRepository.save(student));

    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable(value = "id") Long studentId){
        studentService.deleteById(studentId);
        return "student is deleted";
    }
}
