package com.fss.warrini.entities.quizz_entities;


import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.weaver.patterns.TypePatternQuestions;

@Data
@Entity

@Table(name ="choices_responses")
public class ChoiceResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private ChoiceEntity choice;


    private boolean userValue;
    private boolean userChoiceCorrect;



    @ManyToOne
    private QuestionResponseEntity questionResponse;

    public boolean getUserValue() {
        return userValue;
    }
}
