package com.fss.warrini.entities.quizz_entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ChoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean isCorrect;

    @ManyToOne
    private QuestionEntity question;
}
