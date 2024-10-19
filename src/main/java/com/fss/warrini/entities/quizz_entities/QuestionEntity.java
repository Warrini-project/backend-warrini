package com.fss.warrini.entities.quizz_entities;

import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sentence;

    @ManyToOne
    private QuizzEntity quizz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChoiceEntity> choices;
}
