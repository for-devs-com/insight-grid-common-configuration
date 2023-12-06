package com.fordevs.restcontroller.controller;

import com.fordevs.restcontroller.model.Students;
import com.fordevs.restcontroller.model.SubjectsLearning;
import com.fordevs.restcontroller.repository.StudentsRepository;
import com.fordevs.restcontroller.repository.SubjectsLearningRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Slf4j
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
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        try {
            Optional<Students> students = studentsRepository.findById(id);
            if(students.isPresent()){
                return new ResponseEntity<>(students.get(), HttpStatus.OK);
            }else{
                log.error("Student not found with id: " + id);
                return new ResponseEntity<>("Student not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error getting student with id:" + id, HttpStatus.INTERNAL_SERVER_ERROR);
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
    @Transactional
    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        try {
            // Find Student to delete
            Optional<Students> studentsOptional = studentsRepository.findById(id);
            if(!studentsOptional.isPresent()){
                log.error("Student Not Found with id:" + id);
                return new ResponseEntity<>("Student Not Found with id:" + id, HttpStatus.NOT_FOUND);
            }
            Students students = studentsOptional.get();

            // Find and unlink SubjectsLearnings student references
            List<SubjectsLearning> subjectsLearning = subjectsLearningRepository.findAllByStudentId_Id(students.getId());

            for(SubjectsLearning subject : subjectsLearning) {
                subject.setStudent(null);
                subjectsLearningRepository.save(subject);

            }

            // Delete Student
            studentsRepository.deleteById(id);
            log.info("Student deleted with id:" + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error("Could not delete Student with id:" + id);
            return new ResponseEntity<>("Cant delete student with id: " + id,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
