package com.fordevs.restcontroller.controller;

import com.fordevs.restcontroller.model.Students;
import com.fordevs.restcontroller.repository.StudentsRepository;
import com.fordevs.restcontroller.repository.SubjectsLearningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private SubjectsLearningRepository subjectsLearningRepository;

    // Get All Students
    @GetMapping("/all")
    public ResponseEntity<List<Students>> getAllStudents() {
        try {
            List<Students> students = new ArrayList<>();
            students.addAll(studentsRepository.findAll());
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Student by ID
    @GetMapping("/all/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable("id") Long id) {
        try {
            Students students = studentsRepository.findById(id).get();
            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create Student
    @PostMapping("/student")
    public ResponseEntity<Students> createStudent(@RequestBody Students students) {
        try {
            Students _students = studentsRepository.save(students);
            return new ResponseEntity<>(_students, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Student
    @PutMapping("/student/{id}")
    public ResponseEntity<Students> updateStudent(@PathVariable("id") Long id, @RequestBody Students students) {
        try {
            Students _students = studentsRepository.findById(id).get();
            _students.setStudent_name(students.getStudent_name());
            _students.setStudent_last_name(students.getStudent_last_name());
            _students.setStudent_email(students.getStudent_email());
            _students.setDept_id(students.getDept_id());
            _students.setStudent_status(students.getStudent_status());
            return new ResponseEntity<>(studentsRepository.save(_students), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Student
    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        try {
            subjectsLearningRepository.deleteById(id);
            studentsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
