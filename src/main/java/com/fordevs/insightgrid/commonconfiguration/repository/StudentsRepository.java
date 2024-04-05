package com.fordevs.insightgrid.commonconfiguration.repository;

import com.fordevs.insightgrid.commonconfiguration.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Students, Long> {
}
