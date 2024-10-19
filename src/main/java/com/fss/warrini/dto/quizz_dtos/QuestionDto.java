package com.fss.warrini.dto.quizz_dtos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private Long id;
    private String sentence;

    private Long quizzId;

    @OneToMany
    private List<ChoiceDto> choices;
}
