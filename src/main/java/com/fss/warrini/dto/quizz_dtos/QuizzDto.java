package com.fss.warrini.dto.quizz_dtos;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class QuizzDto {
    private Long id;
    private String title;
    private String description;
    private String image; // store quizz image id stored on mongo db
    private String level;
    private String skill;

    private List<QuestionDto> questions;
}
