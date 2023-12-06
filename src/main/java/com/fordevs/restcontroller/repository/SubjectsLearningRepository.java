package com.fordevs.restcontroller.repository;

import com.fordevs.restcontroller.model.SubjectsLearning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectsLearningRepository extends JpaRepository<SubjectsLearning, Long> {
    void deleteSubjectsLearningById(Long id);
}
