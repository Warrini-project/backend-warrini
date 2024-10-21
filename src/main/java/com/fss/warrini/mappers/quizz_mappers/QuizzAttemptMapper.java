package com.fss.warrini.mappers.quizz_mappers;

import com.fss.warrini.dto.quizz_dtos.QuizzAttemptDto;
import com.fss.warrini.entities.quizz_entities.QuizzAttemptEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {QuestionResponseMapper.class})
public interface QuizzAttemptMapper {


    @Mapping(source = "quizz.id", target = "quizzId")
    @Mapping(source = "user.id", target = "userId")
    QuizzAttemptDto toDto(QuizzAttemptEntity entity);

    @Mapping(source = "quizzId", target = "quizz.id")
    @Mapping(source = "userId", target = "user.id")
    QuizzAttemptEntity toEntity(QuizzAttemptDto dto);
}

