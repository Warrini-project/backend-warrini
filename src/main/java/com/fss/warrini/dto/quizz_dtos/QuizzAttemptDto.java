package com.fss.warrini.dto.quizz_dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


@Data
public class QuizzAttemptDto {
    private Long id;
    private Long quizzId;
    private Long userId;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int score;
    private List<QuestionResponseDto> questionsResponses;

}
