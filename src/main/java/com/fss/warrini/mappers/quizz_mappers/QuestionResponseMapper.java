package com.fss.warrini.mappers.quizz_mappers;

import com.fss.warrini.dto.quizz_dtos.QuestionResponseDto;
import com.fss.warrini.entities.quizz_entities.QuestionResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ChoiceResponseMapper.class})

public interface QuestionResponseMapper {

    QuestionResponseMapper INSTANCE = Mappers.getMapper(QuestionResponseMapper.class);

    @Mapping(source = "question.id", target = "questionId")
    @Mapping(source = "quizzAttempt.id", target = "quizzAttemptId")
    QuestionResponseDto toDto(QuestionResponseEntity entity);

    @Mapping(source = "questionId", target = "question.id")
    @Mapping(source = "quizzAttemptId", target = "quizzAttempt.id")
    QuestionResponseEntity toEntity(QuestionResponseDto dto);
}
