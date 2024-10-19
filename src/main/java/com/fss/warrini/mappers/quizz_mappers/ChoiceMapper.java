package com.fss.warrini.mappers.quizz_mappers;


import com.fss.warrini.dto.quizz_dtos.ChoiceDto;
import com.fss.warrini.dto.quizz_dtos.QuizzDto;
import com.fss.warrini.entities.quizz_entities.ChoiceEntity;
import com.fss.warrini.entities.quizz_entities.QuizzEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChoiceMapper {

    @Mapping(source = "question.id", target = "questionId")
    ChoiceDto toDto(ChoiceEntity quizzEntity);

    @Mapping(source = "questionId", target = "question.id")
    ChoiceEntity toEntity(ChoiceDto dto);

    default ChoiceDto toDtoWithQuestionId(ChoiceEntity choiceEntity, Long questionId) {
        ChoiceDto choiceDto = toDto(choiceEntity);
        choiceDto.setQuestionId(questionId);
        return choiceDto;
    }
}
