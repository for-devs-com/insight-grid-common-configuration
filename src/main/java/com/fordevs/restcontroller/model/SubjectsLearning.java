package com.fordevs.restcontroller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_learning")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectsLearning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub_name")
    private String subject_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Students students;

    @Column(name = "marks_obtained")
    private Long marks_obtained;
}
