package com.fss.warrini.entities.quizz_entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="quizzes")
public class QuizzEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image; // store quizz image id stored on mongo db
    private String level;
    private String domain;

    @OneToMany(mappedBy = "quizz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions;
}
