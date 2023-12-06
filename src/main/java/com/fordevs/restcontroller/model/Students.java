package com.fordevs.restcontroller.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String student_name;

    @Column(name = "last_name")
    private String student_last_name;

    @Column(name = "email")
    private String student_email;

    @Column(name = "dept_id")
    private Long dept_id;

    @Column(name = "is_active")
    private String student_status;

    @OneToMany(mappedBy = "students", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectsLearning> subjectsLearning;

}
