package com.fordevs.insightgrid.commonconfiguration.repository;

import com.fordevs.insightgrid.commonconfiguration.model.SubjectsLearning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectsLearningRepository extends JpaRepository<SubjectsLearning, Long> {
    List<SubjectsLearning> findAllByStudentId_Id(Long studentId);
}
