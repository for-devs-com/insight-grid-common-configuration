package com.fordevs.restcontroller.repository;

import com.fordevs.restcontroller.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
