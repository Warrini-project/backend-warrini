package com.fss.warrini.entities.quizz_entities;


import com.fss.warrini.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="quizzes_attempts")
public class QuizzAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private QuizzEntity quizz;

    @ManyToOne
    private UserEntity user;

    private int score;

    @OneToMany(mappedBy = "quizzAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionResponseEntity> questionsResponses;


}
