package com.fss.warrini.dto.quizz_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
public class ChoiceResponseDto {
    private Long id;
    private Long choiceId;


    private boolean userValue;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean userChoiceCorrect;

    private Long questionResponseId;

    public boolean getUserValue() {
        return userValue;
    }
}
