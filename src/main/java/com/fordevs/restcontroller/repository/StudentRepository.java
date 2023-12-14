package com.fordevs.restcontroller.repository;

import com.fordevs.restcontroller.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
