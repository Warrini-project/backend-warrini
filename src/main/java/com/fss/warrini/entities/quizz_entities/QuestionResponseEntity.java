package com.fss.warrini.entities.quizz_entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="questions_responses")
public class QuestionResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private QuestionEntity question;

    @OneToMany(mappedBy = "questionResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChoiceResponseEntity> choicesResponses;

    private boolean isResponseCorrect;

    @ManyToOne
    private QuizzAttemptEntity quizzAttempt;
}
