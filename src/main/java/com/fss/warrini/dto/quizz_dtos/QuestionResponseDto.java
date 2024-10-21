package com.fss.warrini.dto.quizz_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDto {
    private Long id;

    private Long questionId;
    private Long quizzAttemptId;

    private List<ChoiceResponseDto> choicesResponses;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isResponseCorrect;

}

