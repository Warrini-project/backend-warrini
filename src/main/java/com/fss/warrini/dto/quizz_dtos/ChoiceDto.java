package com.fss.warrini.dto.quizz_dtos;


import jakarta.persistence.*;
import lombok.Data;

@Data
public class ChoiceDto {
    private Long id;
    private String text;
    private boolean isCorrect;

    private Long questionId;
}
